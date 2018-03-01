package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.monapp.model.Cursus;
import com.monapp.model.Stage;

@Transactional
@Repository
public class StageDaoImpl implements StageDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public Stage findByPrimaryKey(Integer id) {
		return em.find(Stage.class, id);
	}

	@Override
	public List<Stage> findAll() {
		String querystring = "SELECT s FROM Stage s ORDER BY id" ;
		Query query = em.createQuery(querystring);
		List<Stage> list = query.getResultList();
		return list;
	}

	@Override
	public Stage save(Stage entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Stage entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Stage update(Stage entity) {
		return em.merge(entity);
	}

}
