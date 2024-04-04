package com.webapp.escola_spring.Repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_spring.Model.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, String>{

    Administrador findByCpf(String cpf);
    // se eu precisar criar algum método extra eu vou criar aqui
}
