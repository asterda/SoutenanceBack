package com.monapp.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("TECH")
public class Technicien extends RessourceHumaine {

	@OneToMany(fetch=FetchType.LAZY, mappedBy="technicien")
	@JsonView(Views.TechnicienEtCursus.class)
	private List<Cursus> cursus;

	public Technicien() {
		super();
	}

	public List<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}

}
