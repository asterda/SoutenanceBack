package com.monapp.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("STAG")
public class Stagiaire extends RessourceHumaine {

	@JsonView(Views.Common.class)
	private String client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView(Views.StagiaireEtCursus.class)
	private Cursus cursus;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stagiaire")
	@JsonView(Views.StagiaireEtEvaluation.class)
	private List<Evaluation> evaluations;

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

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

}
