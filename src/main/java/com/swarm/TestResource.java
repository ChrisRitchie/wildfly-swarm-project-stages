package com.swarm;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

/**
 * This rest resource end point doesn't really adhere to rest standards. There
 * is quite a bit of stuff in here in addition.
 */
@Stateless
@Path("/swarm")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

	@Inject
	@ConfigurationValue("project.stage")
	String stage;

	@Context
	private HttpServletRequest httpRequest;

	@GET
	public Response getStage() {
		return Response.ok("{stage: '" + stage + "'}").build();
	}

}
