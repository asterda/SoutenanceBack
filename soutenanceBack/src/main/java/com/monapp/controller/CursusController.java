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
import com.monapp.dao.CursusDao;
import com.monapp.model.Cursus;
import com.monapp.model.Stage;
import com.monapp.model.Views;

@RestController
@CrossOrigin
public class CursusController {
	
	@Autowired
	CursusDao cursusDao;
	
	@GetMapping("/cursus/{id}")
	@JsonView(Views.CursusGlobal.class)
	public ResponseEntity<Cursus> findOne(@PathVariable("id") Integer id){
		Cursus c = cursusDao.findByPrimaryKey(id);
		if(c == null) {
			return new ResponseEntity<Cursus>(c, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Cursus>(c, HttpStatus.OK);
		}
	}
	
	@GetMapping("/cursus")
	@JsonView(Views.CursusGlobal.class)
	public ResponseEntity<List<Cursus>> findAll() {
		List<Cursus> cursus = cursusDao.findAll();
		return new ResponseEntity<List<Cursus>>(cursus, HttpStatus.OK);
	}
	
	@DeleteMapping("/cursus/{id}")	
	public ResponseEntity<Cursus> delete(@PathVariable("id") Integer id){
		Cursus tmp = cursusDao.findByPrimaryKey(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				cursusDao.delete(tmp);								
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PostMapping("/cursus")
	@JsonView(Views.CursusGlobal.class)
	public ResponseEntity<Cursus> create(@RequestBody Cursus cursus) {
		if (cursus.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		cursusDao.save(cursus);
		return new ResponseEntity<Cursus>(cursus, HttpStatus.CREATED);
	}
	
	@PutMapping("/cursus")
	@JsonView(Views.CursusGlobal.class)
	public ResponseEntity<Cursus> update(@RequestBody Cursus cursus) {
		if (cursus.getId() == null) {
			return create(cursus);
		}
		cursus = cursusDao.update(cursus);

		return new ResponseEntity<Cursus>(cursus, HttpStatus.OK);
	}

}
