package com.monapp.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.RessourceMaterielle;

@Transactional
@Repository
public class RessourceMaterielleDaoImpl implements RessourceMaterielleDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public RessourceMaterielle findByPrimaryKey(String id) {
		return em.find(RessourceMaterielle.class, id);
	}

	@Override
	public List<RessourceMaterielle> findAll() {
		String querystring = "SELECT r FROM RessourceMaterielle r ORDER BY id";
		Query query = em.createQuery(querystring);
		List<RessourceMaterielle> list = query.getResultList();
		return list;
	}

	@Override
	public RessourceMaterielle save(RessourceMaterielle entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(RessourceMaterielle entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public RessourceMaterielle update(RessourceMaterielle entity) {
		return em.merge(entity);
	}

}
