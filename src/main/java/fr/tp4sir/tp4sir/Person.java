package fr.tp4sir.tp4sir;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
public class Person{

	@Column(name="id_person")
	long id;
	private String nom;
	private String prenom;
	private String unmail;
	
	
	private Collection<Home> residences;
	
	
	private Collection<Person> friends;
	
	
	private Collection<ElectronicDevice> devices;
	
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Person(String nom, String prenom, String unmail, Collection<Home> residences, Collection<Person> friends) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.unmail = unmail;
		this.residences = residences;
		this.friends = friends;
	}
	@Id @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUnmail() {
		return unmail;
	}

	public void setUnmail(String unmail) {
		this.unmail = unmail;
	}
	@OneToMany(mappedBy="person_homme", cascade=CascadeType.PERSIST)
	public Collection<Home> getResidences() {
		return residences;
	}

	public void setResidences(Collection<Home> residences) {
		this.residences = residences;
	}
	@OneToMany(mappedBy="friends", cascade=CascadeType.PERSIST)
	public Collection<Person> getFriends() {
		return friends;
	}

	public void setFriends(Collection<Person> friends) {
		this.friends = friends;
	}
	
	@OneToMany(mappedBy="personnes", cascade=CascadeType.PERSIST)
	public Collection<ElectronicDevice> getDevices() {
		return devices;
	}

	public void setDevices(Collection<ElectronicDevice> devices) {
		this.devices = devices;
	}
	
	
}
