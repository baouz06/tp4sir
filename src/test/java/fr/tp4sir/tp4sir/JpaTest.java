package fr.tp4sir.tp4sir;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;




public class JpaTest {

	private static EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Person personne = new Person();
		  personne.setNom("baouz");
          personne.setPrenom("khaled");
    
          personne.setUnmail("email1");
		
          
          Person personne2 = new Person();
          personne2.setNom("talay");
          personne2.setPrenom("emri");
    
          personne2.setUnmail("email2");
          
          
          
          Person personne3 = new Person();
          personne3.setNom("julien");
          personne3.setPrenom("sezar");
    
          personne3.setUnmail("email3");
          // CREATION DE MAISON
          Home home = new Home();
          home.setNombre_de_piece(2);
          home.setTaille(50);
          home.setPerson_homme(personne);
          
          Home home2 = new Home();
          home2.setNombre_de_piece(2);
          home2.setTaille(50);
          home2.setPerson_homme(personne);
          
          
          
          
          Home home3 = new Home();
          home3.setNombre_de_piece(2);
          home3.setTaille(50);
          home3.setPerson_homme(personne2);
          
          ElectronicDevice ELECTRONICdEVICE = new ElectronicDevice();
          ELECTRONICdEVICE.setCconsommation(100);
          ELECTRONICdEVICE.setPersonnes(personne);
          
          ElectronicDevice ELECTRONICdEVICE2 = new ElectronicDevice();
          ELECTRONICdEVICE2.setCconsommation(200);
          ELECTRONICdEVICE2.setPersonnes(personne2);
          
          ElectronicDevice ELECTRONICdEVICE3 = new ElectronicDevice();
          ELECTRONICdEVICE3.setCconsommation(50);
          ELECTRONICdEVICE3.setPersonnes(personne3);
          
          Heater heater = new Heater();
          
          
          
          
          
          manager.persist(ELECTRONICdEVICE);
          manager.persist(ELECTRONICdEVICE2);
          manager.persist(ELECTRONICdEVICE3);
          manager.persist(home);
          manager.persist(home2); manager.persist(home3);
          manager.persist(personne);
          manager.persist(personne2);
          manager.persist(personne3);
          
          
          
          
          tx.commit();
	  
	}

	



}
