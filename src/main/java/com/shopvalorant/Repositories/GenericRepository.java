package com.shopvalorant.Repositories;

public interface GenericRepository<E> {
    E save(E t);

    E read(Long id);

    E update(E t);

    void delete(E t);
}
