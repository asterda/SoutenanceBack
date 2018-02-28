package com.monapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.Technicien;

@Transactional
@Repository
public class TechnicienDaoImpl implements TechnicienDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public Technicien findByPrimaryKey(Integer id) {
		return em.find(Technicien.class, id);
	}

	@Override
	public List<Technicien> findAll() {
		String querystring = "SELECT t FROM Technicien t ORDER BY id" ;
		Query query = em.createQuery(querystring);
		List<Technicien> list = query.getResultList();
		return list;
	}

	@Override
	public Technicien save(Technicien entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Technicien entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Technicien update(Technicien entity) {
		return em.merge(entity);
	}

}
