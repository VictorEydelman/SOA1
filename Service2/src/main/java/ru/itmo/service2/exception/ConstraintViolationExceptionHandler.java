package ru.itmo.service2.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ru.itmo.service2.domain.Error;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response.status(400)
                .entity(Error.builder()
                        .error(400)
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_XML)
                .build();
    }
}
