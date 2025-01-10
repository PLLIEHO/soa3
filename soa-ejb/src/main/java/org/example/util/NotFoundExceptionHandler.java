package org.example.util;

import com.spo.entity.dto.Errors;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    @Context
    private HttpHeaders headers;

    public Response toResponse(NotFoundException ex){
        Errors err = new Errors();
        err.addError(404, "Url not found");
        return Response.status(404).entity(err).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

}