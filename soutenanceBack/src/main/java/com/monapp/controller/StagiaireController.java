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
import com.monapp.dao.StagiaireDao;
import com.monapp.model.Stagiaire;
import com.monapp.model.Views;

@RestController
@CrossOrigin
public class StagiaireController {

	@Autowired
	StagiaireDao stagiaireDao;
	
	@GetMapping("/stagiaires/{id}")
	@JsonView(Views.StagiaireGlobal.class)
	public ResponseEntity<Stagiaire> findOne(@PathVariable("id") Integer id){
		Stagiaire s = stagiaireDao.findByPrimaryKey(id);
		
		if(s == null) {
			return new ResponseEntity<Stagiaire>(s, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Stagiaire>(s, HttpStatus.OK);
		}
	}
	
	@GetMapping("/stagiaires")
	@JsonView(Views.StagiaireGlobal.class)
	public ResponseEntity<List<Stagiaire>> findAll() {
		List<Stagiaire> stagiaires = stagiaireDao.findAll();
		return new ResponseEntity<List<Stagiaire>>(stagiaires, HttpStatus.OK);
	}
	
	@DeleteMapping("/stagiaires/{id}")	
	public ResponseEntity<Stagiaire> delete(@PathVariable("id") Integer id){
		Stagiaire tmp = stagiaireDao.findByPrimaryKey(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				stagiaireDao.delete(tmp);								
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PostMapping("/stagiaires")
	@JsonView(Views.StagiaireGlobal.class)
	public ResponseEntity<Stagiaire> create(@RequestBody Stagiaire stagiaire) {
		if (stagiaire.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		stagiaireDao.save(stagiaire);
		return new ResponseEntity<Stagiaire>(stagiaire, HttpStatus.CREATED);
	}
	
	@PutMapping("/stagiaires")
	@JsonView(Views.StagiaireGlobal.class)
	public ResponseEntity<Stagiaire> update(@RequestBody Stagiaire stagiaire) {
		if (stagiaire.getId() == null) {
			return create(stagiaire);
		}
		stagiaire = stagiaireDao.update(stagiaire);

		return new ResponseEntity<Stagiaire>(stagiaire, HttpStatus.OK);
	}

}
