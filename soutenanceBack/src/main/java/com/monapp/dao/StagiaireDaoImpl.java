package com.monapp.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.Stagiaire;

@Transactional
@Repository
public class StagiaireDaoImpl implements StagiaireDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public Stagiaire findByPrimaryKey(Integer id) {
		return em.find(Stagiaire.class, id);
	}

	@Override
	public List<Stagiaire> findAll() {
		String querystring = "SELECT s FROM Stagiaire s ORDER BY id" ;
		Query query = em.createQuery(querystring);
		List<Stagiaire> list = query.getResultList();
		return list;
	}

	@Override
	public Stagiaire save(Stagiaire entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Stagiaire entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Stagiaire update(Stagiaire entity) {
		return em.merge(entity);
	}
	
}
