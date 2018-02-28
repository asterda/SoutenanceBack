package com.monapp.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("FORM")
public class Formateur extends RessourceHumaine {

	@ManyToMany(fetch=FetchType.LAZY)
	private List<Matiere> matieres; // Compétences du formateur == ce qu'il peut enseigner
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="formateur")
	private List<Module> modules; // Cours dispensés par le formateur == ce qu'il enseigne vraiment

	public Formateur() {
		super();
	}

	public List<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

}