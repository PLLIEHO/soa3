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
                                           "ID": "%s",
                                           "Name": "testservice",
                                           "Tags": [
                                             "primary"
                                           ],
                                           "port": 8080,
                                           "Address": "localhost",
                                           "check": {
                                           "name": "Worker check",
                                           "http": "http://localhost:8080",
                                           "method": "GET",
                                           "interval": "10s"
                                           }
                         }
        
        """, id);


        Response response = client
                .target(target)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(req, MediaType.APPLICATION_JSON));
        System.out.println("ИНДУСЫ ВПЕРЕД");
//        Consul client = Consul.builder().withHostAndPort(HostAndPort.fromParts("localhost", 18987)).build();
//        AgentClient agentClient = client.agentClient();
//
//        Registration reg = ImmutableRegistration.builder()
//                .id(id)
//                .name("soa-controller")
//                .port(8080)
//                .check(Registration.RegCheck.ttl(3L))
//                .build();
//
//        agentClient.register(reg);
//
//        agentClient.pass(id);

        serviceId = id;
    }

//    @PreDestroy
//    public void destroy(){
//        System.out.println("ИНДУСЫ НАЗАД");
//
//        Consul client = Consul.builder().withHostAndPort(HostAndPort.fromParts("localhost", 18987)).build();
//        AgentClient agentClient = client.agentClient();
//
//        agentClient.deregister(serviceId);
//    }


}
