package com.monapp.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("GEST")
public class Gestionnaire extends RessourceHumaine {

	@OneToMany(fetch=FetchType.LAZY, mappedBy="gestionnaire")
	private List<Cursus> cursus;

	public Gestionnaire() {
		super();
	}

	public List<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}

}
