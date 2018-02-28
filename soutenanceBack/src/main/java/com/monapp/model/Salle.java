package com.monapp.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("SALLE")
public class Salle extends RessourceMaterielle {

	private Integer capacite;
	private Integer etage;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="salle")
	private List<Cursus> cursus;

	public Salle() {
		super();
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public Integer getEtage() {
		return etage;
	}

	public void setEtage(Integer etage) {
		this.etage = etage;
	}

	public List<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}

}
