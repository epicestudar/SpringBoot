package com.webapp.escola_spring.Repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_spring.Model.Aluno;
import com.webapp.escola_spring.Model.LancarNotas;

public interface LancarNotasRepository extends CrudRepository<LancarNotas, Long> {
    LancarNotas findByIdNota(long idNota);
    List<LancarNotas> findByNomeAluno(Aluno aluno);
}

