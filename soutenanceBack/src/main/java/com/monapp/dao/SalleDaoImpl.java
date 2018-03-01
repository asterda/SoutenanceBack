package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.Salle;

@Transactional
@Repository
public class SalleDaoImpl implements SalleDao{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Salle findByPrimaryKey(String code) {
		return em.find(Salle.class, code);
		}

	@Override
	public List<Salle> findAll() {
		String querystring = "SELECT s FROM Salle s ORDER BY code";
		Query query = em.createQuery(querystring);
		List<Salle> list = query.getResultList();
		return list;
	}

	@Override
	public Salle save(Salle entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Salle entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Salle update(Salle entity) {
		return em.merge(entity);
	}

}
