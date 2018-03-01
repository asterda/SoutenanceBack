
package com.monapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.monapp.dao.GestionnaireDao;
import com.monapp.model.Gestionnaire;
import com.monapp.model.Views;

@RestController
@CrossOrigin
public class GestionnaireController {

	@Autowired
	GestionnaireDao gestionnaireDao;
	
	@GetMapping("/gestionnaires/{id}")
	@JsonView(Views.GestionnaireGlobal.class)
	public ResponseEntity<Gestionnaire> findOne(@PathVariable("id") Integer id){
		Gestionnaire g = gestionnaireDao.findByPrimaryKey(id);
		
		if(g == null) {
			return new ResponseEntity<Gestionnaire>(g, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Gestionnaire>(g, HttpStatus.OK);
		}
	}
	
	@GetMapping("/gestionnaires")
	@JsonView(Views.GestionnaireGlobal.class)
	public ResponseEntity<List<Gestionnaire>> findAll() {
		List<Gestionnaire> formateurs = gestionnaireDao.findAll();
		return new ResponseEntity<List<Gestionnaire>>(formateurs, HttpStatus.OK);
	}
	
	@DeleteMapping("/gestionnaires/{id}")	
	public ResponseEntity<Gestionnaire> delete(@PathVariable("id") Integer id){
		Gestionnaire tmp = gestionnaireDao.findByPrimaryKey(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				gestionnaireDao.delete(tmp);								
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PostMapping("/gestionnaires")
	@JsonView(Views.GestionnaireGlobal.class)
	public ResponseEntity<Gestionnaire> create(@RequestBody Gestionnaire gestionnaire) {
		if (gestionnaire.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		gestionnaireDao.save(gestionnaire);
		return new ResponseEntity<Gestionnaire>(gestionnaire, HttpStatus.CREATED);
	}
	
	@PutMapping("/gestionnaires")
	@JsonView(Views.GestionnaireGlobal.class)
	public ResponseEntity<Gestionnaire> update(@RequestBody Gestionnaire gestionnaire) {
		if (gestionnaire.getId() == null) {
			return create(gestionnaire);
		}
		gestionnaire = gestionnaireDao.update(gestionnaire);

		return new ResponseEntity<Gestionnaire>(gestionnaire, HttpStatus.OK);
	}
}
