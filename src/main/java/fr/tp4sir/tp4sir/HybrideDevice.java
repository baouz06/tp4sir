package fr.tp4sir.tp4sir;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class HybrideDevice {
	@Column(name="id_Hybride")
private long id;
private String name;

public HybrideDevice() {
	// TODO Auto-generated constructor stub
}
public HybrideDevice(String name) {
	super();
	this.name = name;
}
@Id @GeneratedValue
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
