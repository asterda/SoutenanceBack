package com.monapp.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.Evaluation;

@Transactional
@Repository
public class EvaluationDaoImpl implements EvaluationDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public Evaluation findByPrimaryKey(Integer id) {
		return em.find(Evaluation.class, id);
	}

	@Override
	public List<Evaluation> findAll() {
		String querystring = "SELECT e FROM Evaluation e ORDER BY id";
		Query query = em.createQuery(querystring);
		List<Evaluation> list = query.getResultList();
		return list;
	}

	@Override
	public Evaluation save(Evaluation entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Evaluation entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Evaluation update(Evaluation entity) {
		return em.merge(entity);
	}
	
}
