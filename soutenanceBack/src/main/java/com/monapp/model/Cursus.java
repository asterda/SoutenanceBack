package com.monapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cursus {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String titre;
	
	@Temporal(TemporalType.DATE) // /!\ yyyy-MM-dd : type postgresql
	private Date dateDebut;		 // /!\ type Java
	
	@Temporal(TemporalType.DATE) // /!\ yyyy-MM-dd : type postgresql
	private Date dateFin;		 // /!\ type Java
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Gestionnaire gestionnaire;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cursus")
	private List<Module> modules;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Technicien technicien;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Salle salle;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Videoprojecteur videoprojecteur;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cursus")
	private List<Stage> stages;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cursus")
	private List<Stagiaire> stagiaires;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Ordinateur> ordinateurs;

	public Cursus() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Technicien getTechnicien() {
		return technicien;
	}

	public void setTechnicien(Technicien technicien) {
		this.technicien = technicien;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Videoprojecteur getVideoprojecteur() {
		return videoprojecteur;
	}

	public void setVideoprojecteur(Videoprojecteur videoprojecteur) {
		this.videoprojecteur = videoprojecteur;
	}

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public List<Ordinateur> getOrdinateurs() {
		return ordinateurs;
	}

	public void setOrdinateurs(List<Ordinateur> ordinateurs) {
		this.ordinateurs = ordinateurs;
	}

}
