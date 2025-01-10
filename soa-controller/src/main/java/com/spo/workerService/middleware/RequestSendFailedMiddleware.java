package com.spo.workerService.middleware;

import com.spo.entity.dto.Errors;
import jakarta.annotation.Priority;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.ejb.client.RequestSendFailedException;

@Provider
@Priority(1)
public class RequestSendFailedMiddleware implements ExceptionMapper<RequestSendFailedException> {

    @Context
    private HttpHeaders headers;

    public Response toResponse(RequestSendFailedException ex){
        Errors err = new Errors();
        err.addError(503, "External service unavailable");
        return Response.status(503).entity(err).type(MediaType.APPLICATION_JSON_TYPE).build();
    }


}