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
import com.monapp.dao.ModuleDao;
import com.monapp.model.Module;
import com.monapp.model.Stage;
import com.monapp.model.Views;

@RestController
@CrossOrigin
public class ModuleController {

	@Autowired
	ModuleDao moduleDao;
	
	@GetMapping("/modules/{id}")
	@JsonView(Views.ModuleGlobal.class)
	public ResponseEntity<Module> findOne(@PathVariable("id") Integer id){
		Module m = moduleDao.findByPrimaryKey(id);
		if(m == null) {
			return new ResponseEntity<Module>(m, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Module>(m, HttpStatus.OK);
		}
	}
	
	@GetMapping("/modules")
	@JsonView(Views.ModuleGlobal.class)
	public ResponseEntity<List<Module>> findAll() {
		List<Module> modules = moduleDao.findAll();
		return new ResponseEntity<List<Module>>(modules, HttpStatus.OK);
	}
	
	@DeleteMapping("/modules/{id}")	
	public ResponseEntity<Module> delete(@PathVariable("id") Integer id){
		Module tmp = moduleDao.findByPrimaryKey(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				moduleDao.delete(tmp);								
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PostMapping("/modules")
	@JsonView(Views.ModuleGlobal.class)
	public ResponseEntity<Module> create(@RequestBody Module module) {
		if (module.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		moduleDao.save(module);
		return new ResponseEntity<Module>(module, HttpStatus.CREATED);
	}
	
	@PutMapping("/modules")
	@JsonView(Views.ModuleGlobal.class)
	public ResponseEntity<Module> update(@RequestBody Module module) {
		if (module.getId() == null) {
			return create(module);
		}
		module = moduleDao.update(module);
		return new ResponseEntity<Module>(module, HttpStatus.OK);
	}
	
}
