package com.webapp.escola_spring.Repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_spring.Model.VerificaCadastroAluno;

public interface VerificaCadastroAlunoRepository extends CrudRepository<VerificaCadastroAluno, String>{
    VerificaCadastroAlunoRepository findByRa(String ra);
}
