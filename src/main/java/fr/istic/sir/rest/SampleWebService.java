package fr.istic.sir.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.tp4sir.tp4sir.Heater;
import fr.tp4sir.tp4sir.Home;

@Path("/hello")
public class SampleWebService {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello, how are you?";
	}

	@GET
	@Path("/home")
	@Produces(MediaType.APPLICATION_JSON)
	public Home getHome() {
		Home home1 = new Home();
		home1.setTaille(30);
		Heater heater1 = new Heater();
		heater1.setPuissance("500w");
		Heater heater2 = new Heater();
		heater2.setPuissance("600w");
		Collection<Heater> heaters = new ArrayList();
		heaters.add(heater1);
		heaters.add(heater2);
		home1.setChauffage(heaters);

		return home1;
	}
}