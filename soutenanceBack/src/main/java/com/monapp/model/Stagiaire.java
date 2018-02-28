package com.monapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("STAG")
public class Stagiaire extends RessourceHumaine {

	@JsonView(Views.Common.class)
	private String client;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonView(Views.StagiaireEtCursus.class)
	private Cursus cursus;

	public Stagiaire() {
		super();
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}

}
