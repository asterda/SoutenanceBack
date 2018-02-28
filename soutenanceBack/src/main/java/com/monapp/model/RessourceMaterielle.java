package com.monapp.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="RM_TYPE", discriminatorType=DiscriminatorType.STRING)
public abstract class RessourceMaterielle {

	@Id
	private String code;
	private Float coutJour;

	public RessourceMaterielle() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Float getCoutJour() {
		return coutJour;
	}

	public void setCoutJour(Float coutJour) {
		this.coutJour = coutJour;
	}

}
