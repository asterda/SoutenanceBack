package com.monapp.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.RessourceHumaine;

@Transactional
@Repository
public class RessourceHumaineDaoImpl implements RessourceHumaineDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public RessourceHumaine findByPrimaryKey(Integer id) {
		return em.find(RessourceHumaine.class, id);
	}

	@Override
	public List<RessourceHumaine> findAll() {
		String querystring = "SELECT r FROM RessourceHumaine r ORDER BY id";
		Query query = em.createQuery(querystring);
		List<RessourceHumaine> list = query.getResultList();
		return list;
	}

	@Override
	public RessourceHumaine save(RessourceHumaine entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(RessourceHumaine entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public RessourceHumaine update(RessourceHumaine entity) {
		return em.merge(entity);
	}

}
