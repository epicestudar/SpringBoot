package com.webapp.escola_spring.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_spring.Model.Aluno;
import com.webapp.escola_spring.Model.LancarFaltas;

public interface LancarFaltasRepository extends CrudRepository<LancarFaltas, Long>{
    LancarFaltas findByIdFalta(long idFalta);
    List<LancarFaltas> findByNomeAluno(Aluno aluno);
}
