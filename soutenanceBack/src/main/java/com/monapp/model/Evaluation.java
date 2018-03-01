package com.monapp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Evaluation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private Integer id;
	
	@JsonView(Views.Common.class)
	private Integer note; // Sur 100
	
	@JsonView(Views.Common.class)
	private String positif;
	
	@JsonView(Views.Common.class)
	private String negatif;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonView(Views.EvaluationEtModule.class)
	private Module module;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonView(Views.EvaluationEtStagiaire.class)
	private Stagiaire stagiaire;

	public Evaluation() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public String getPositif() {
		return positif;
	}

	public void setPositif(String positif) {
		this.positif = positif;
	}

	public String getNegatif() {
		return negatif;
	}

	public void setNegatif(String negatif) {
		this.negatif = negatif;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

}
