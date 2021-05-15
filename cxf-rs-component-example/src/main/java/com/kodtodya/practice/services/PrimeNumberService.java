package com.kodtodya.practice.services;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Service
public interface PrimeNumberService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/check-prime/{number}/")
	public String checkPrime(@PathParam("number") int number);

}