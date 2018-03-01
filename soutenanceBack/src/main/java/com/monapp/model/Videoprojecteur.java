package com.monapp.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("VP")
public class Videoprojecteur extends RessourceMaterielle {
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="videoprojecteur")
	@JsonView(Views.VideoprojecteurEtCursus.class)
	private List<Cursus> cursus;

	public Videoprojecteur() {
		super();
	}

	public List<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}

}
