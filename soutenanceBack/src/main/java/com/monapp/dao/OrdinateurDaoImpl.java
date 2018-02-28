package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.monapp.model.Ordinateur;
import com.monapp.model.Salle;

@Transactional
@Repository
public class OrdinateurDaoImpl implements OrdinateurDao{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Ordinateur findByPrimaryKey(String code) {
		return em.find(Ordinateur.class, code);
	}

	@Override
	public List<Ordinateur> findAll() {
		String querystring = "SELECT o FROM Ordinateur o ORDER BY code";
		Query query = em.createQuery(querystring);
		List<Ordinateur> list = query.getResultList();
		return list;
	}

	@Override
	public Ordinateur save(Ordinateur entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Ordinateur entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Ordinateur update(Ordinateur entity) {
		return em.merge(entity);
	}

}
