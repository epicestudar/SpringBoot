package com.webapp.escola_spring.Repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_spring.Model.Aluno;
import com.webapp.escola_spring.Model.Materias;

import java.util.List;


public interface AlunoRepository extends CrudRepository<Aluno, String>{
    Aluno findByRa(String ra);
    Aluno findByNome(String nomeAluno);
    List<Aluno> findByMaterias(Materias materias);
}
