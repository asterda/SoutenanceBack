package com.monapp.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.monapp.model.Module;

@Transactional
@Repository
public class ModuleDaoImpl implements ModuleDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Module findByPrimaryKey(Integer id) {
		return em.find(Module.class, id);
	}

	@Override
	public List<Module> findAll() {
		String querystring = "SELECT m FROM Module m ORDER BY id" ;
		Query query = em.createQuery(querystring);
		List<Module> list = query.getResultList();
		return list;
	}

	@Override
	public Module save(Module entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Module entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public Module update(Module entity) {
		return em.merge(entity);
	}

}
