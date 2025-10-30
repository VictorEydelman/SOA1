package se.ifmo.soa.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import se.ifmo.soa.DAO.Error;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(404)
                .entity(Error.builder()
                        .error(404)
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_XML)
                .build();
    }
}
