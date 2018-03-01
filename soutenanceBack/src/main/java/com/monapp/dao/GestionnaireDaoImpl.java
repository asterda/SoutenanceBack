package com.monapp.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.Gestionnaire;

@Transactional
@Repository
public class GestionnaireDaoImpl implements GestionnaireDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public Gestionnaire findByPrimaryKey(Integer id) {
		return em.find(Gestionnaire.class, id);
	}

	@Override
	public List<Gestionnaire> findAll() {
		String querystring = "SELECT g FROM Gestionnaire g ORDER BY id" ;
		Query query = em.createQuery(querystring);
		List<Gestionnaire> list = query.getResultList();
		return list;
	}

	@Override
	public Gestionnaire save(Gestionnaire entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Gestionnaire entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Gestionnaire update(Gestionnaire entity) {
		return em.merge(entity);
	}

}
