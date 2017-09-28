package com.dayang.share4.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class DynamicQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public <T> TypedQuery<T> executeHql(String hql, Class<T> tClass) {
        return entityManager.createQuery(hql, tClass);
    }
}
