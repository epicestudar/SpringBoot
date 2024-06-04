package com.example.locadora_carros.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.locadora_carros.Model.Carros;


public interface CarrosRP extends CrudRepository<Carros, Long>{
    Carros findByIdCarro(Long idCarro);
}
