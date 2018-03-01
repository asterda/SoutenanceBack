package com.monapp.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.monapp.model.Cursus;

@Transactional
@Repository
public class CursusDaoImpl implements CursusDao {

	@PersistenceContext
	EntityManager em;

	public Cursus findByPrimaryKey(Integer id) {
		return em.find(Cursus.class, id);
	}
	
	@Override
	public List<Cursus> findAll() {
		String querystring = "SELECT c FROM Cursus c ORDER BY id" ;
		Query query = em.createQuery(querystring);
		List<Cursus> list = query.getResultList();
		return list;
	}

	@Override
	public Cursus save(Cursus entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Cursus entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Cursus update(Cursus entity) {
		return em.merge(entity);
	}
	
}
