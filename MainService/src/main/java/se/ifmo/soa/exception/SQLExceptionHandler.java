package se.ifmo.soa.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import se.ifmo.soa.DAO.Error;

import java.sql.SQLException;

@Provider
public class SQLExceptionHandler implements ExceptionMapper<SQLException> {
    @Override
    public Response toResponse(SQLException e) {
        e.printStackTrace();
        return Response.status(503)
                .entity(Error.builder()
                        .error(503)
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_XML)
                .build();
    }
}
