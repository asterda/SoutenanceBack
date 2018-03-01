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
import com.monapp.dao.VideoprojecteurDao;
import com.monapp.model.Videoprojecteur;
import com.monapp.model.Views;

@RestController
@CrossOrigin
public class VideoprojecteurController {
	
	@Autowired
	VideoprojecteurDao videoprojecteurDao;
	
	@GetMapping("/videoprojecteurs/{code}")
	@JsonView(Views.VideoprojecteurGlobal.class)
	public ResponseEntity<Videoprojecteur> findOne(@PathVariable("code") String code){
		Videoprojecteur v = videoprojecteurDao.findByPrimaryKey(code);
		
		if(v == null) {
			return new ResponseEntity<Videoprojecteur>(v, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Videoprojecteur>(v, HttpStatus.OK);
		}
	}
	
	@GetMapping("/videoprojecteurs")
	@JsonView(Views.VideoprojecteurGlobal.class)
	public ResponseEntity<List<Videoprojecteur>> findAll() {
		List<Videoprojecteur> videoprojecteurs = videoprojecteurDao.findAll();
		return new ResponseEntity<List<Videoprojecteur>>(videoprojecteurs, HttpStatus.OK);
	}
	
	@DeleteMapping("/videoprojecteurs/{code}")	
	public ResponseEntity<Videoprojecteur> delete(@PathVariable("code") String code){
		Videoprojecteur tmp = videoprojecteurDao.findByPrimaryKey(code);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				videoprojecteurDao.delete(tmp);								
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PostMapping("/videoprojecteurs")
	@JsonView(Views.VideoprojecteurGlobal.class)
	public ResponseEntity<Videoprojecteur> create(@RequestBody Videoprojecteur videoprojecteur) {
		if (videoprojecteur.getCode() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		videoprojecteurDao.save(videoprojecteur);
		return new ResponseEntity<Videoprojecteur>(videoprojecteur, HttpStatus.CREATED);
	}
	
	@PutMapping("/videoprojecteurs")
	@JsonView(Views.VideoprojecteurGlobal.class)
	public ResponseEntity<Videoprojecteur> update(@RequestBody Videoprojecteur videoprojecteur) {
		if (videoprojecteur.getCode() == null) {
			return create(videoprojecteur);
		}
		videoprojecteur = videoprojecteurDao.update(videoprojecteur);

		return new ResponseEntity<Videoprojecteur>(videoprojecteur, HttpStatus.OK);
	}

}