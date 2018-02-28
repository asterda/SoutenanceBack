package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.Matiere;
import com.monapp.model.MatierePK;

@Transactional
@Repository
public class MatiereDaoImpl implements MatiereDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Matiere findByPrimaryKey(MatierePK id) {
		return em.find(Matiere.class, id);
	}

	@Override
	public List<Matiere> findAll() {
		String querystring = "SELECT m FROM Matiere m" ;
		Query query = em.createQuery(querystring);
		List<Matiere> list = query.getResultList();
		return list;
	}

	@Override
	public Matiere save(Matiere entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Matiere entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Matiere update(Matiere entity) {
		return em.merge(entity);
	}

}
