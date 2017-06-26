package com.ynm.exception.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Yogesh.Manware
 *
 */
@Provider
public class ParameterQueueException extends Exception implements
		ExceptionMapper<ParameterQueueException> {
	private static final long serialVersionUID = 1L;

	public ParameterQueueException() {
		super("Error Occurred while persisting Parameters!");
	}

	public ParameterQueueException(String string) {
		super(string);
	}

	@Override
	public Response toResponse(ParameterQueueException exception) {
		return Response.status(404).entity(exception.getMessage())
				.type("text/plain").build();
	}
}