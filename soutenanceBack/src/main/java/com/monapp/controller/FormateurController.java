
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
import com.monapp.dao.FormateurDao;
import com.monapp.model.Formateur;
import com.monapp.model.Views;

@RestController
@CrossOrigin
public class FormateurController {
	
	@Autowired
	FormateurDao formateurDao;
	
	@GetMapping("/formateurs/{id}")
	@JsonView(Views.FormateurGlobal.class)
	public ResponseEntity<Formateur> findOne(@PathVariable("id") Integer id){
		Formateur s = formateurDao.findByPrimaryKey(id);
		
		if(s == null) {
			return new ResponseEntity<Formateur>(s, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Formateur>(s, HttpStatus.OK);
		}
	}
	
	@GetMapping("/formateurs")
	@JsonView(Views.FormateurGlobal.class)
	public ResponseEntity<List<Formateur>> findAll() {
		List<Formateur> formateurs = formateurDao.findAll();
		return new ResponseEntity<List<Formateur>>(formateurs, HttpStatus.OK);
	}
	
	@DeleteMapping("/formateurs/{id}")	
	public ResponseEntity<Formateur> delete(@PathVariable("id") Integer id){
		Formateur tmp = formateurDao.findByPrimaryKey(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				formateurDao.delete(tmp);								
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PostMapping("/formateurs")
	@JsonView(Views.FormateurGlobal.class)
	public ResponseEntity<Formateur> create(@RequestBody Formateur formateur) {
		if (formateur.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		formateurDao.save(formateur);
		return new ResponseEntity<Formateur>(formateur, HttpStatus.CREATED);
	}
	
	@PutMapping("/formateurs")
	@JsonView(Views.FormateurGlobal.class)
	public ResponseEntity<Formateur> update(@RequestBody Formateur formateur) {
		if (formateur.getId() == null) {
			return create(formateur);
		}
		formateur = formateurDao.update(formateur);

		return new ResponseEntity<Formateur>(formateur, HttpStatus.OK);
	}

}
