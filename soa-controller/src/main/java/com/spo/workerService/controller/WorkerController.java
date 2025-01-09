package com.spo.workerService.controller;

import com.spo.entity.dto.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.WorkerServiceRemote;
import org.example.repository.WorkerRepository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;

@Path("api/workers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkerController {

    WorkerServiceRemote workerService;

    private static final Log log = LogFactory.getLog(WorkerRepository.class);

    public WorkerController() {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        props.put("jboss.naming.client.ejb.context", true);
        props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        try {
            InitialContext ctx = new InitialContext(props);
            workerService = (WorkerServiceRemote) ctx.lookup("ejb:/soa-ejb/WorkerService!org.example.WorkerServiceRemote");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    @GET
    public Response getWorker(@QueryParam("page_size") Optional<Integer> pageSize,
                              @QueryParam("page_offset") Optional<Integer> pageOffset,
                              @QueryParam("sort") List<String> sort,
                              @QueryParam("filter") List<String> filter){
            GetRequest req = new GetRequest();
            req.setPageSize(pageSize.orElse(0));
            if (req.getPageSize() < 0){
                return wrongResponse(422, "Page size can not be less than 0");
            }
            req.setPageOffset(pageOffset.orElse(0));
            if (req.getPageSize() < 0){
                return wrongResponse(422, "Page offset can not be less than 0");
            }
            req.setSort(sort);
            req.setFilter(filter);
            if (req.getSort() == null) {
                req.setSort(new ArrayList<>());
            }
            if (req.getFilter() == null) {
                req.setFilter(new ArrayList<>());
            }
        var res = workerService.getWorkers(req);
        return createResponse(res, 200);
        
    }

    @POST
    public Response createWorker(CreateWorkerDTO worker){
        log.info("Get post request");
        var res = workerService.createWorker(worker);
        return createResponse(res, 200);
    }
    @GET
    @Path("/{worker-id}")
    public Response getWorker(@PathParam("worker-id") String workerId){
        log.info("Get get request");
        try{
            Integer.parseInt(workerId);
        } catch (NumberFormatException e){
            return wrongResponse(422, "Illegal id(can not be string)");
        }
        var res = workerService.getWorker(workerId);
        return createResponse(res, 200);
    }

    @DELETE
    @Path("/{worker-id}")
    public Response deleteWorker(@PathParam("worker-id") String workerId){
        log.info("Get delete request");
        try{
            Integer.parseInt(workerId);
        } catch (NumberFormatException e){
            return wrongResponse(422, "Illegal id(can not be string)");
        }
        var res = workerService.deleteWorker(workerId);
        return createResponse(res, 204);
    }

    @PATCH
    @Path("/{worker-id}")
    public Response patchWorker(@PathParam("worker-id") String workerId, EditWorkerDTO worker){
        log.info("Get patch request");
        int workId;
        try{
            workId = Integer.parseInt(workerId);
        } catch (NumberFormatException e){
            return wrongResponse(422, "Illegal id(can not be string)");
        }
        var res = workerService.patchWorker(workId, worker);
        return createResponse(res, 200);
    }

    @GET
    @Path("/salary")
    public Response getSalary(){
        log.info("Get salary request");
        var res = workerService.getSalary();
        return createResponse(res, 200);
    }

    @POST
    @Path("/person")
    public Response findCountWorkers(FindPersonDTO personDTO){
        log.info("Get person request");
        var res = workerService.findCountWorkers(personDTO);
        return createResponse(res, 200);
    }

    public Response createResponse(List<Object> res, int status){
        Errors errList = new Errors();

        if (res.get(1) != null){
            for (var err: (ArrayList<String>) res.get(1)){
                errList.addError((int) res.get(2), err);
            }
            return Response.status((int) res.get(2)).entity(errList).build();
        }
        try{
            res.get(3);
        } catch (IndexOutOfBoundsException e){
            return Response.ok().entity(res.get(0)).build();
        }
        Map<String, Boolean> map = new HashMap<>();
        map.put("NextPage", (boolean) res.get(3));
        List<Object> out = new ArrayList<>();
        out.add(res.get(0));
        out.add(map);
        return Response.status(status).entity(out).build();
    }

    public Response wrongResponse(int status, String error){
        Errors errors = new Errors();
        errors.addError(status, error);
        return Response.status(status).entity(errors).build();
    }
}

