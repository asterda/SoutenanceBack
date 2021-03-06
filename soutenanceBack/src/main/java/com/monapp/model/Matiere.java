package com.monapp.model;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Matiere {

	@EmbeddedId
	@JsonView(Views.Common.class)
	private MatierePK id;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "matieres")
	@JsonView(Views.MatiereEtModules.class)
	private List<Module> modules;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "matieres")
	@JsonView(Views.MatiereEtFormateurs.class)
	private List<Formateur> formateurs;

	public Matiere() {
		super();
	}
	
	public MatierePK getId() {
		return id;
	}

	public void setId(MatierePK id) {
		this.id = id;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

}
