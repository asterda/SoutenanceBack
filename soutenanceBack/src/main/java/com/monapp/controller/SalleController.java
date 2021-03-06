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
import com.monapp.dao.SalleDao;
import com.monapp.model.Salle;
import com.monapp.model.Views;

@RestController
@CrossOrigin
public class SalleController {

	@Autowired
	SalleDao salleDao;
	
	@GetMapping("/salles/{code}")
	@JsonView(Views.SalleGlobal.class)
	public ResponseEntity<Salle> findOne(@PathVariable("code") String code){
		Salle s = salleDao.findByPrimaryKey(code);
		
		if(s == null) {
			return new ResponseEntity<Salle>(s, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Salle>(s, HttpStatus.OK);
		}
	}
	
	@GetMapping("/salles")
	@JsonView(Views.SalleGlobal.class)
	public ResponseEntity<List<Salle>> findAll() {
		List<Salle> salles = salleDao.findAll();
		return new ResponseEntity<List<Salle>>(salles, HttpStatus.OK);
	}
	
	@DeleteMapping("/salles/{code}")	
	public ResponseEntity<Salle> delete(@PathVariable("code") String code){
		Salle tmp = salleDao.findByPrimaryKey(code);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		try {
			salleDao.delete(tmp);							
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/salles")
	@JsonView(Views.SalleGlobal.class)
	public ResponseEntity<Salle> create(@RequestBody Salle salle) {
		if (salle.getCode() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			salleDao.save(salle);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Salle>(salle, HttpStatus.CREATED);
	}
	
	@PutMapping("/salles")
	@JsonView(Views.SalleGlobal.class)
	public ResponseEntity<Salle> update(@RequestBody Salle salle) {
		if (salle.getCode() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // On ne peut pas créer une salle sans code, car le code n'est pas auto-généré
		}
		try {
			salle = salleDao.update(salle);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Salle>(salle, HttpStatus.OK);
	}

}