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
import com.monapp.dao.StageDao;
import com.monapp.model.Stage;
import com.monapp.model.Views;

@RestController
@CrossOrigin
public class StageController {
	
	@Autowired
	StageDao stageDao;
	
	@GetMapping("/stages/{id}")
	@JsonView(Views.StageGlobal.class)
	public ResponseEntity<Stage> findOne(@PathVariable("id") Integer id){
		Stage s = stageDao.findByPrimaryKey(id);
		
		if(s == null) {
			return new ResponseEntity<Stage>(s, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Stage>(s, HttpStatus.OK);
		}
	}
	
	@GetMapping("/stages")
	@JsonView(Views.StageGlobal.class)
	public ResponseEntity<List<Stage>> findAll() {
		List<Stage> stages = stageDao.findAll();
		return new ResponseEntity<List<Stage>>(stages, HttpStatus.OK);
	}
	
	@DeleteMapping("/stages/{id}")	
	public ResponseEntity<Stage> delete(@PathVariable("id") Integer id){
		Stage tmp = stageDao.findByPrimaryKey(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				stageDao.delete(tmp);								
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PostMapping("/stages")
	@JsonView(Views.StageGlobal.class)
	public ResponseEntity<Stage> create(@RequestBody Stage stage) {
		if (stage.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			stageDao.save(stage);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Stage>(stage, HttpStatus.CREATED);
	}
	
	@PutMapping("/stages")
	@JsonView(Views.StageGlobal.class)
	public ResponseEntity<Stage> update(@RequestBody Stage stage) {
		if (stage.getId() == null) {
			return create(stage);
		}
		try {
			stage = stageDao.update(stage);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Stage>(stage, HttpStatus.OK);
	}
	
}

