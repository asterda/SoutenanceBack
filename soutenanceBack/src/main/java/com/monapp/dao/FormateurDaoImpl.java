package com.monapp.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.Formateur;

@Transactional
@Repository
public class FormateurDaoImpl implements FormateurDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public Formateur findByPrimaryKey(Integer id) {
		return em.find(Formateur.class, id);
	}

	@Override
	public List<Formateur> findAll() {
		String querystring = "SELECT f FROM Formateur f ORDER BY id";
		Query query = em.createQuery(querystring);
		List<Formateur> list = query.getResultList();
		return list;
	}

	@Override
	public Formateur save(Formateur entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Formateur entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Formateur update(Formateur entity) {
		return em.merge(entity);
	}
}
