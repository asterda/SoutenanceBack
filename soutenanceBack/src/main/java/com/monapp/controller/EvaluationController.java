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
import com.monapp.dao.EvaluationDao;
import com.monapp.model.Evaluation;
import com.monapp.model.Formateur;
import com.monapp.model.Views;

@RestController
@CrossOrigin
public class EvaluationController {
	
	@Autowired
	EvaluationDao evaluationDao;
	
	@GetMapping("/evaluations/{id}")
	@JsonView(Views.EvaluationGlobal.class)
	public ResponseEntity<Evaluation> findOne(@PathVariable("id") Integer id){
		Evaluation e = evaluationDao.findByPrimaryKey(id);
		
		if(e == null) {
			return new ResponseEntity<Evaluation>(e, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Evaluation>(e, HttpStatus.OK);
		}
	}
	
	@GetMapping("/evaluations")
	@JsonView(Views.EvaluationGlobal.class)
	public ResponseEntity<List<Evaluation>> findAll() {
		List<Evaluation> evaluations = evaluationDao.findAll();
		return new ResponseEntity<List<Evaluation>>(evaluations, HttpStatus.OK);
	}
	
	@DeleteMapping("/evaluations/{id}")	
	public ResponseEntity<Evaluation> delete(@PathVariable("id") Integer id){
		Evaluation tmp = evaluationDao.findByPrimaryKey(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				evaluationDao.delete(tmp);								
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PostMapping("/evaluations")
	@JsonView(Views.EvaluationGlobal.class)
	public ResponseEntity<Evaluation> create(@RequestBody Evaluation evaluation) {
		if (evaluation.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			evaluationDao.save(evaluation);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Evaluation>(evaluation, HttpStatus.CREATED);
	}
	
	@PutMapping("/evaluations")
	@JsonView(Views.EvaluationGlobal.class)
	public ResponseEntity<Evaluation> update(@RequestBody Evaluation evaluation) {
		if (evaluation.getId() == null) {
			return create(evaluation);
		}
		try {
			evaluation = evaluationDao.update(evaluation);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Evaluation>(evaluation, HttpStatus.OK);
	}

}
