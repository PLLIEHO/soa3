package com.spo.workerService;



import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Random;

@ApplicationPath("/")
@Produces({"application/json"})
public class main extends Application {
    String serviceId;

    @PostConstruct
    public void init() throws NotRegisteredException {
        Client client = ClientBuilder.newClient();
        String target = "http://localhost:18987/v1/agent/service/register";
        Random rn = new Random();
        String id = rn.nextInt(10000 - 1 + 1) + 1 + "";
        String req = String.format("""
                {
                                           "id": "%s",
                                           "name": "testservice",
                                           "tags": [
                                             "primary"
                                           ],
                                           "port": 8080,
                                           "address": "localhost",
                                           "checks": [{
                                            "name": "Worker check http",
                                            "http": "http://localhost:8080/api/workers/health",
                                            "method": "GET",
                                            "interval": "10s"
                                           }]
                         }
        
        """, id);


        Response response = client
                .target(target)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(req, MediaType.APPLICATION_JSON));

        System.out.println(req);

        serviceId = id;
    }


    @PreDestroy
    public void destroy() {
        Client client = ClientBuilder.newClient();
        String target = "http://localhost:18987/v1/agent/service/deregister" + serviceId;

        System.out.println(serviceId);

        Response response = client
                .target(target)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(null));
    }


}
