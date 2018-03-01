package com.monapp.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("SALLE")
public class Salle extends RessourceMaterielle {

	@JsonView(Views.Common.class)
	private Integer capacite;
	
	@JsonView(Views.Common.class)
	private Integer etage;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="salle")
	@JsonView(Views.SalleEtCursus.class)
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
