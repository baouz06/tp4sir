package fr.tp4sir.tp4sir;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONNE")
public class Person {

	private long id;
	@Column(name = "Nom")
	private String name;
	@Column(name = "Pr�nom")
	private String firstName;
	@Column(name = "E-mail")
	private String mail;

	private List<Person> friends;
	private List<Home> residences;

	public Person(String nam, String fn, String mail) {
		this.name = nam;
		this.firstName = fn;
		this.mail = mail;
		this.setFriends(new ArrayList<Person>());
		this.setResidences(new ArrayList<Home>());
	}

	public Person() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Person [nom=" + name + ", prenom=" + firstName + ", email=" + mail + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(cascade = CascadeType.PERSIST)
	public List<Home> getResidences() {
		return residences;
	}

	public void setResidences(List<Home> residences) {
		this.residences = residences;
	}

	@ManyToMany
	@JoinTable(name = "amis")
	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
}
