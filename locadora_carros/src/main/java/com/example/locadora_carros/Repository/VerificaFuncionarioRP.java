package com.example.locadora_carros.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.locadora_carros.Model.VerificaFuncionario;

public interface VerificaFuncionarioRP extends CrudRepository<VerificaFuncionario, String>{
    VerificaFuncionario findByEmail(String email);
}
