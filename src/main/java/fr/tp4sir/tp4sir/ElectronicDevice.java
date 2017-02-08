package fr.tp4sir.tp4sir;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("ElectronicDevice")
public class ElectronicDevice extends HybrideDevice{

	
	private long consommation;
	private Person personnes;
	
	public ElectronicDevice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ElectronicDevice(long cconsommation) {
		super();
		this.consommation = cconsommation;
	}
	

	public long getCconsommation() {
		return consommation;
	}
	public void setCconsommation(long cconsommation) {
		this.consommation = cconsommation;
	}
	@ManyToOne
	public Person getPersonnes() {
		return personnes;
	}
	public void setPersonnes(Person personnes) {
		this.personnes = personnes;
	}
	
	
}
