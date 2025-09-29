package ru.itmo.service2.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ru.itmo.service2.domain.Error;

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
                .build();
    }
}
