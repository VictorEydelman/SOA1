package se.ifmo.soa.exception;

import jakarta.persistence.NoResultException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import se.ifmo.soa.DAO.Error;

@Provider
public class NoResultExceptionHandler implements ExceptionMapper<NoResultException> {
    @Override
    public Response toResponse(NoResultException e) {
        return Response.status(404)
                .entity(Error.builder()
                        .error(404)
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_XML)
                .build();
    }
}
