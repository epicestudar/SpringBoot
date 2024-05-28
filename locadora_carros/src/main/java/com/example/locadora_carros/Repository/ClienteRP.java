package com.example.locadora_carros.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.locadora_carros.Model.Cliente;

public interface ClienteRP extends CrudRepository<Cliente, Long>{

}
