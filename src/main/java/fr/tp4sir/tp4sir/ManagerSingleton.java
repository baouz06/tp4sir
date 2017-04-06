package fr.tp4sir.tp4sir;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerSingleton {
	
	private static EntityManager manager;
	private static EntityManagerFactory factory;
	private static ManagerSingleton m =null;


	private ManagerSingleton() {
		
		factory = Persistence.createEntityManagerFactory("example");
		manager = factory.createEntityManager();
		m=this;
     	
	}
	
	
	
	public static ManagerSingleton getInstance() {
		if (m == null) { // Premier appel
	         m = new ManagerSingleton();
			}
		
		return m;
	}
	
	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		ManagerSingleton.manager = manager;
	}

	public EntityManagerFactory getFactory() {
		return factory;
	}

	public void setFactory(EntityManagerFactory factory) {
		ManagerSingleton.factory = factory;
	}
}
