package com.webapp.escola_spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webapp.escola_spring.Model.Aluno;
import com.webapp.escola_spring.Model.LancarNotas;

@Repository
public interface LancarNotasRepository extends JpaRepository<LancarNotas, Long> {
    // List<LancarNotas> findByAluno(String aluno);
    // Aluno findByAluno(Aluno aluno);
}
