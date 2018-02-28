package com.monapp.model;

public interface Views {

	// Common
	public interface Common{ }
	
	// Formateur
	public interface Formateur extends Common { }
	public interface FormateurEtMatieres extends Formateur { }
	public interface FormateurEtModules extends Formateur { }
	public interface FormateurEtIndisponibilites extends Formateur { }
	public interface FormateurGlobal extends FormateurEtMatieres, FormateurEtModules, FormateurEtIndisponibilites { }
	
	// Technicien
	public interface Technicien extends Common { }
	public interface TechnicienEtCursus extends Technicien { }
	public interface TechnicienGlobal extends TechnicienEtCursus { }
	
	// Gestionnaire
	public interface Gestionnaire extends Common { }
	public interface GestionnaireEtCursus extends Gestionnaire { }
	public interface GestionnaireGlobal extends GestionnaireEtCursus { }
	
	// Stagiaire
	public interface Stagiaire extends Common { }
	public interface StagiaireEtCursus extends Stagiaire { }
	public interface StagiaireGlobal extends StagiaireEtCursus { }
	
	// Matiere
	public interface Matiere extends Common { }
	public interface MatiereEtModules extends Matiere { }
	public interface MatiereEtFormateurs extends Matiere { }
	public interface MatiereGlobal extends MatiereEtModules, MatiereEtFormateurs { }
	
	// Indisponibilite
	public interface Indisponibilite extends Common { }
	public interface IndisponibiliteEtFormateur extends Indisponibilite { }
	
	// Module
	public interface Module extends Common { }
	public interface ModuleEtMatieres extends Module { }
	public interface ModuleEtCursus extends Module { }
	public interface ModuleEtFormateur extends Module { }
	public interface ModuleGlobal extends ModuleEtMatieres, ModuleEtCursus, ModuleEtFormateur { }
	
	// Stage
	public interface Stage extends Common { }
	public interface StageEtCursus extends Stage { }
	public interface StageGlobal extends StageEtCursus { }
	
}
