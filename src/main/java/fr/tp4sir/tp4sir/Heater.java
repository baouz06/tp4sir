package fr.tp4sir.tp4sir;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("Heater")
public class Heater extends HybrideDevice  {


	private String nom_chauffage ;
	private String puissance;
	private Home homes;
	
	public Heater() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Heater(String nom_chauffage, String puissance,Home homes) {
		super();
		this.nom_chauffage = nom_chauffage;
		this.puissance = puissance;
		this.homes = homes;
	}

	public String getNom_chauffage() {
		return nom_chauffage;
	}
	public void setNom_chauffage(String nom_chauffage) {
		this.nom_chauffage = nom_chauffage;
	}
	public String getPuissance() {
		return puissance;
	}
	public void setPuissance(String puissance) {
		this.puissance = puissance;
	}
	
	@ManyToOne
	public Home getHomes() {
		return homes;
	}
	public void setHomes(Home homes) {
		this.homes = homes;
	}
	
	
	
}
