package com.webapp.escola_spring.Repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_spring.Model.Docente;

public interface DocenteRepository extends CrudRepository<Docente, String>{
    Docente findByEmailInstitucional(String emailInstitucional);
    // Docente deleteByEmailInstitucional(String emailInstitucional);
}