package com.webapp.escola_spring.Repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_spring.Model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, String>{
    Aluno findByRa(String ra);
}
