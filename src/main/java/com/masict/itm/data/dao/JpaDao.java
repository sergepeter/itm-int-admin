package com.masict.itm.data.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class JpaDao<K, E> implements Dao<K, E> {
	protected Class<E> entityClass;

	@PersistenceContext 
	public EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public JpaDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass
				.getActualTypeArguments()[1];
	}

	public void persist(E entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	public E getById(K id){
		return entityManager.getReference(entityClass, id);
	}
	
	public void remove(E entity) {

		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();

	}

	public E findById(K id) {
		return entityManager.find(entityClass, id);
	}

	/**
	 * The find all query in generic mode (there is perhaps some simplest way...)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<E> findAll() {
		
		// we need to find the real non generic type name
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		Class entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];
		
		List<E> list = new ArrayList<E>();
		
		List results = entityManager.createQuery("select c from " + entityClass.getSimpleName() + " c").getResultList();
		
		for (Object s : results ){
			list.add((E) s);
		}
		 return list;
	}


	public void setEntityManager(EntityManager entityManager ) {
		this.entityManager = entityManager;
	}
	
	
}