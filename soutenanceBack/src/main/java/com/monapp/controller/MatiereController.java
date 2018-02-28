package com.monapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.monapp.dao.MatiereDao;
import com.monapp.model.Matiere;
import com.monapp.model.MatierePK;
import com.monapp.model.Niveau;
import com.monapp.model.Stage;
import com.monapp.model.Technologie;

@RestController
@CrossOrigin
public class MatiereController {
	
	@Autowired
	MatiereDao matiereDao;
	
	@GetMapping("/matieres/{techno}/{niveau}")
	public ResponseEntity<Matiere> findOne(
			@PathVariable("techno") String technoStr,
			@PathVariable("niveau") String niveauStr
			){
		// On crée d'abord la clef primaire composite
		Matiere m = null;
		try {
			Technologie techno = Technologie.valueOf(technoStr);
			Niveau niveau = Niveau.valueOf(niveauStr);
			MatierePK id = new MatierePK(techno, niveau);
			m = matiereDao.findByPrimaryKey(id);
		}catch(Exception e) {
			return new ResponseEntity<Matiere>(HttpStatus.BAD_REQUEST);
		}
		
		// Controlleur rest habituel
		if(m == null) {
			return new ResponseEntity<Matiere>(m, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Matiere>(m, HttpStatus.OK);
		}
	}
	
	

}
