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
import com.monapp.dao.OrdinateurDao;
import com.monapp.model.Ordinateur;
import com.monapp.model.Views;

@RestController
@CrossOrigin
public class OrdinateurController {

	@Autowired
	OrdinateurDao ordinateurDao;

	@GetMapping("/ordinateurs/{code}")
	@JsonView(Views.OrdinateurGlobal.class)
	public ResponseEntity<Ordinateur> findOne(@PathVariable("code") String code) {
		Ordinateur o = ordinateurDao.findByPrimaryKey(code);

		if (o == null) {
			return new ResponseEntity<Ordinateur>(o, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Ordinateur>(o, HttpStatus.OK);
		}
	}

	@GetMapping("/ordinateurs")
	@JsonView(Views.OrdinateurGlobal.class)
	public ResponseEntity<List<Ordinateur>> findAll() {
		List<Ordinateur> salles = ordinateurDao.findAll();
		return new ResponseEntity<List<Ordinateur>>(salles, HttpStatus.OK);
	}

	@DeleteMapping("/ordinateurs/{code}")
	public ResponseEntity<Ordinateur> delete(@PathVariable("code") String code) {
		Ordinateur tmp = ordinateurDao.findByPrimaryKey(code);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				ordinateurDao.delete(tmp);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/ordinateurs")
	@JsonView(Views.OrdinateurGlobal.class)
	public ResponseEntity<Ordinateur> create(@RequestBody Ordinateur ordinateur) {

		if (ordinateur.getCode() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ordinateurDao.save(ordinateur);
		return new ResponseEntity<Ordinateur>(ordinateur, HttpStatus.CREATED);
	}

	@PutMapping("/ordinateurs")
	@JsonView(Views.OrdinateurGlobal.class)
	public ResponseEntity<Ordinateur> update(@RequestBody Ordinateur ordinateur) {
		if (ordinateur.getCode() == null) {
			return create(ordinateur);
		}
		ordinateur = ordinateurDao.update(ordinateur);

		return new ResponseEntity<Ordinateur>(ordinateur, HttpStatus.OK);
	}

}
