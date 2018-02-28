package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.Videoprojecteur;

@Transactional
@Repository
public class VideoprojecteurDaoImpl implements VideoprojecteurDao{
	@PersistenceContext
	EntityManager em;

	@Override
	public Videoprojecteur findByPrimaryKey(String code) {
		return em.find(Videoprojecteur.class, code);
	}

	@Override
	public List<Videoprojecteur> findAll() {
		String querystring = "SELECT v FROM Videoprojecteur v ORDER BY code";
		Query query = em.createQuery(querystring);
		List<Videoprojecteur> list = query.getResultList();
		return list;
	}

	@Override
	public Videoprojecteur save(Videoprojecteur entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Videoprojecteur entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Videoprojecteur update(Videoprojecteur entity) {
		return em.merge(entity);
	}
}
