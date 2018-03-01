package com.monapp.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.Indisponibilite;

@Transactional
@Repository
public class IndisponibiliteDaoImpl implements IndisponibiliteDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Indisponibilite findByPrimaryKey(Integer id) {
		return em.find(Indisponibilite.class, id);
	}

	@Override
	public List<Indisponibilite> findAll() {
		String querystring = "SELECT i FROM Indisponibilite i ORDER BY id" ;
		Query query = em.createQuery(querystring);
		List<Indisponibilite> list = query.getResultList();
		return list;
	}

	@Override
	public Indisponibilite save(Indisponibilite entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Indisponibilite entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Indisponibilite update(Indisponibilite entity) {
		return em.merge(entity);
	}

}
