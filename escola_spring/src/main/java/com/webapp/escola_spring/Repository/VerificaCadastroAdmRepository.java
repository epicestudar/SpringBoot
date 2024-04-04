package com.webapp.escola_spring.Repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_spring.Model.VerificaCadastroAdm;


public interface VerificaCadastroAdmRepository extends CrudRepository<VerificaCadastroAdm, String>{
    VerificaCadastroAdm findByCpf(String cpf);
}
