package com.ynm.exception.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Yogesh.Manware
 *
 */
@Provider
public class ParameterPersistException extends Exception implements
		ExceptionMapper<ParameterPersistException> {
	private static final long serialVersionUID = 1L;

	public ParameterPersistException() {
		super("Error Occurred while persisting Parameters!");
	}

	public ParameterPersistException(String string) {
		super(string);
	}

	@Override
	public Response toResponse(ParameterPersistException exception) {
		return Response.status(404).entity(exception.getMessage())
				.type("text/plain").build();
	}
}