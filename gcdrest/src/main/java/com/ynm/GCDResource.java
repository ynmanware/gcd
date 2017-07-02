package com.ynm;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.ynm.exception.ParameterQueueException;
import com.ynm.model.Parameters;
import com.ynm.service.GCDRestService;

@Path("gcd")
// http:localhost:8080/gcdrest/webapi/gcd
public class GCDResource {

	@Autowired
	GCDRestService gcdService;

	@POST
	@Path("parameters")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response pushParameters(Parameters params,
			@HeaderParam("apiKey") String apiKey) {
		String key = gcdService.processParameters(params, apiKey);
		return Response.ok()
				.entity("Parameters Successfully Processed with key " + key)
				.build();
	}

	@GET
	@Path("parameters")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Parameters> getAllParameters(
			@HeaderParam("apiKey") String apiKey) {
		if (apiKey == null) {
			throw new ParameterQueueException("Please provide an API key");
		}
		return gcdService.getAllParameters(apiKey);
	}
}
