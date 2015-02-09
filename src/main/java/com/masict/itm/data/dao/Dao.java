package com.masict.itm.data.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface Dao<K, E> {
    void persist(E entity);
    E getById(K id);
    void remove(E entity);
    List<E> findAll();
    // in order to be able to test easily...
    void setEntityManager(EntityManager entityManager );
   
}
