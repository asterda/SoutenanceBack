package com.monapp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class MatierePK implements Serializable {

	private String technologie;
	
	@Enumerated(EnumType.STRING)
	private Niveau niveau;

	public MatierePK() { }

	public MatierePK(String technologie, Niveau niveau) {
		this.technologie = technologie;
		this.niveau = niveau;
	}

	public String getTechnologie() {
		return technologie;
	}

	public void setTechnologie(String technologie) {
		this.technologie = technologie;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((niveau == null) ? 0 : niveau.hashCode());
		result = prime * result + ((technologie == null) ? 0 : technologie.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatierePK other = (MatierePK) obj;
		if (niveau != other.niveau)
			return false;
		if (technologie != other.technologie)
			return false;
		return true;
	}
	
	
	
	
}
