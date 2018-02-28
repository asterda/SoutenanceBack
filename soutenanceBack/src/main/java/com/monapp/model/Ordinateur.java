package com.monapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("ORDI")
public class Ordinateur extends RessourceMaterielle {

	@JsonView(Views.Common.class)
	private String processeur;
	
	@JsonView(Views.Common.class)
	private Integer ramGiga;
	
	@JsonView(Views.Common.class)
	private Integer ddGiga;
	
	@Temporal(TemporalType.DATE) // :!\ yyyy-MM-dd : type postgresql
	@JsonView(Views.Common.class)
	private Date dateAchat; // dd/MM/yyyy
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="ordinateurs")
	@JsonView(Views.OrdinateurEtCursus.class)
	private List<Cursus> cursus;

	public Ordinateur() {
		super();
	}

	public String getProcesseur() {
		return processeur;
	}

	public void setProcesseur(String processeur) {
		this.processeur = processeur;
	}

	public Integer getRamGiga() {
		return ramGiga;
	}

	public void setRamGiga(Integer ramGiga) {
		this.ramGiga = ramGiga;
	}

	public Integer getDdGiga() {
		return ddGiga;
	}

	public void setDdGiga(Integer ddGiga) {
		this.ddGiga = ddGiga;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public List<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}

}
