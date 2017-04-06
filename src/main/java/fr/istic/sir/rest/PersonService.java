package fr.istic.sir.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.tp4sir.tp4sir.ManagerSingleton;
import fr.tp4sir.tp4sir.Person;


@Path("/person")
public class PersonService {
	private ManagerSingleton ManagerSingleton;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAllPersons() {
		this.ManagerSingleton = ManagerSingleton.getInstance();
		EntityManager manager = this.ManagerSingleton.getManager();
		List<Person> resultList = manager.createQuery("Select p From Person as p").getResultList();
		return resultList;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPersonsById(@PathParam("id") long id    ) {
		this.ManagerSingleton = ManagerSingleton.getInstance();
		EntityManager manager = this.ManagerSingleton.getManager();
		Person personne = manager.find(Person.class, id);
		return  personne;
	}
		
	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	public void addPersons(Person p) {
		this.ManagerSingleton = ManagerSingleton.getInstance();
		EntityManager manager = this.ManagerSingleton.getManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.persist(p);
		tx.commit();
	}
	
}
