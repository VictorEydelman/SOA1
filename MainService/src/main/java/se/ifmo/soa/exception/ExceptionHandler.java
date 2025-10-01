package se.ifmo.soa.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import se.ifmo.soa.DAO.Error;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        e.printStackTrace();
        return Response.status(500)
                .entity(Error.builder()
                        .error(500)
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_XML)
                .build();
    }
}
