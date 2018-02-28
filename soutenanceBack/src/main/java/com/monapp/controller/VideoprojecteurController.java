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

import com.monapp.dao.VideoprojecteurDao;
import com.monapp.model.Videoprojecteur;

@RestController
@CrossOrigin
public class VideoprojecteurController {
	
	@Autowired
	VideoprojecteurDao videoprojecteurDao;
	
	@GetMapping("/videoprojecteurs/{code}")
	public ResponseEntity<Videoprojecteur> findOne(@PathVariable("code") String code){
		Videoprojecteur v = videoprojecteurDao.findByPrimaryKey(code);
		
		if(v == null) {
			return new ResponseEntity<Videoprojecteur>(v, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Videoprojecteur>(v, HttpStatus.OK);
		}
	}
	
	@GetMapping("/videoprojecteurs")
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
	public ResponseEntity<Videoprojecteur> create(@RequestBody Videoprojecteur Videoprojecteur) {
		if (Videoprojecteur.getCode() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		videoprojecteurDao.save(Videoprojecteur);
		return new ResponseEntity<Videoprojecteur>(Videoprojecteur, HttpStatus.CREATED);
	}
	
	@PutMapping("/videoprojecteurs")
	public ResponseEntity<Videoprojecteur> update(@RequestBody Videoprojecteur Videoprojecteur) {
		if (Videoprojecteur.getCode() == null) {
			return create(Videoprojecteur);
		}
		Videoprojecteur = videoprojecteurDao.update(Videoprojecteur);

		return new ResponseEntity<Videoprojecteur>(Videoprojecteur, HttpStatus.OK);
	}

}