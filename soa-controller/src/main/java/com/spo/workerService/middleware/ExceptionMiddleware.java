package com.spo.workerService.middleware;

import com.spo.entity.dto.Errors;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.ejb.client.RequestSendFailedException;

@Provider
public class ExceptionMiddleware implements ExceptionMapper<Exception> {

    @Context
    private HttpHeaders headers;

    public Response toResponse(Exception ex){
        Errors err = new Errors();
        err.addError(500, "Sorry service unavailable");
        return Response.status(500).entity(err).type(MediaType.APPLICATION_JSON_TYPE).build();
    }


}