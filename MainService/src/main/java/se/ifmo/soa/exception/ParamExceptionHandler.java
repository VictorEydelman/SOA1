package se.ifmo.soa.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.server.ParamException;
import se.ifmo.soa.DAO.Error;

@Provider
public class ParamExceptionHandler implements ExceptionMapper<ParamException> {
    @Override
    public Response toResponse(ParamException e) {
        String message = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
        return Response.status(400)
                .entity(Error.builder()
                        .error(400)
                        .message(message)
                        .build())
                .type(MediaType.APPLICATION_XML)
                .build();
    }
}
