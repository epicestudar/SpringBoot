package com.webapp.escola_spring.Repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_spring.Model.Materias;


public interface MateriasRepository extends CrudRepository<Materias, Long>{
    // Materias findByIdMateria(long idMateria);
}
