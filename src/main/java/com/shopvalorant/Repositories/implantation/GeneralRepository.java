package com.shopvalorant.Repositories.implantation;

import com.shopvalorant.Repositories.GenericRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class GeneralRepository<E> implements GenericRepository<E> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<E> eClass;

    public void seteClass(Class<E> eClass) {
        this.eClass = eClass;
    }

    @Override
    public E save(E e) {
        entityManager.persist(e);
        return e;
    }

    @Override
    public E read(Long id) {
        return entityManager.find(eClass, id);
    }

    @Override
    public E update(E e) {
        e = entityManager.merge(e);
        return e;
    }

    @Override
    public void delete(E e) {
        entityManager.remove(e);
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }
}