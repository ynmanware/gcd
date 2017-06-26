package com.ynm;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ynm.model.Parameters;
import com.ynm.service.ParameterService;
import com.ynm.service.ParameterServiceImpl;

@Path("gcd")
// http:localhost:8080/gcdrest/webapi/gcd
public class ParametersResource {

	ParameterService parameterService = new ParameterServiceImpl();

	@POST
	@Path("parameters")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response pushParameters(Parameters params) {
		parameterService.processParameters(params, "TODO");	
		return Response.ok().entity("Parameters Successfully Processed.")
				.build();
	}

	@GET
	@Path("parameters")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Parameters> getAllParameters() {
		return parameterService.getAllParameters("TODO");
	}
}
