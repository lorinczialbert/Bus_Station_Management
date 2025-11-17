package com.example.busstation.repository;

import com.example.busstation.model.BaseEntity;


import java.util.List;
import java.util.Optional;

/**
 * Interfață generică (contract) pentru operațiunile CRUD.
 * T = Tipul modelului (ex. Bus)
 * ID = Tipul ID-ului (ex. String)
 */
public interface IRepository<T extends BaseEntity, ID>
{

    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    void delete(ID id);
}