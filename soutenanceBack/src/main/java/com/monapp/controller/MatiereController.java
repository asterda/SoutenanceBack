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
import com.monapp.dao.MatiereDao;
import com.monapp.model.Matiere;
import com.monapp.model.MatierePK;
import com.monapp.model.Niveau;

@RestController
@CrossOrigin
public class MatiereController {

	@Autowired
	MatiereDao matiereDao;

	@GetMapping("/matieres/{techno}/{niveau}")
	public ResponseEntity<Matiere> findOne(@PathVariable("techno") String techno,
			@PathVariable("niveau") String niveauStr) {
		// On crée d'abord la clef primaire composite
		Matiere m = null;
		try {
			Niveau niveau = Niveau.valueOf(niveauStr);
			MatierePK id = new MatierePK(techno, niveau);
			m = matiereDao.findByPrimaryKey(id);
		} catch (Exception e) {
			return new ResponseEntity<Matiere>(HttpStatus.BAD_REQUEST);
		}

		// Controlleur rest habituel
		if (m == null) {
			return new ResponseEntity<Matiere>(m, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Matiere>(m, HttpStatus.OK);
		}
	}

	@GetMapping("/matieres")
	public ResponseEntity<List<Matiere>> findAll() {
		List<Matiere> matieres = matiereDao.findAll();
		return new ResponseEntity<List<Matiere>>(matieres, HttpStatus.OK);
	}

	@DeleteMapping("/matieres/{techno}/{niveau}")
	public ResponseEntity<Matiere> delete(@PathVariable("techno") String techno,
			@PathVariable("niveau") String niveauStr) {
		Matiere m = null;
		try {
			Niveau niveau = Niveau.valueOf(niveauStr);
			MatierePK id = new MatierePK(techno, niveau);
			m = matiereDao.findByPrimaryKey(id);
		} catch (Exception e) {
			return new ResponseEntity<Matiere>(HttpStatus.BAD_REQUEST);
		}

		if (m == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				matiereDao.delete(m);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/matieres")
	public ResponseEntity<Matiere> create(@RequestBody Matiere matiere) {
		/*
		 * ATT: ici, l'id n'est pas auto-généré
		 */
		if (matiere.getId() == null) { // L'id DOIT être renseigné par le client
			return new ResponseEntity<Matiere>(HttpStatus.BAD_REQUEST);
		}
		try {
			matiereDao.save(matiere); // L'id doit être valide
		} catch (Exception e) {
			return new ResponseEntity<Matiere>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Matiere>(matiere, HttpStatus.CREATED);
	}

	@PutMapping("/matieres")
	public ResponseEntity<Matiere> update(@RequestBody Matiere matiere) {
		if (matiere.getId() == null) {
			return new ResponseEntity<Matiere>(HttpStatus.BAD_REQUEST); // L'id ne peut pas être autogénéré
		}

		try {
			matiere = matiereDao.update(matiere);
			return new ResponseEntity<Matiere>(matiere, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
