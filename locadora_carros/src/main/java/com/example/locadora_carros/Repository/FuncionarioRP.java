package com.example.locadora_carros.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.locadora_carros.Model.Funcionario;

public interface FuncionarioRP extends CrudRepository<Funcionario, String>{
    Funcionario findByEmail(String email);
}
