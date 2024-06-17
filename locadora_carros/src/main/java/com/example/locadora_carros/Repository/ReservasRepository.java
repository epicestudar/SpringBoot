package com.example.locadora_carros.Repository;

import org.springframework.data.repository.CrudRepository;
import com.example.locadora_carros.Model.Reserva;

public interface ReservasRepository extends CrudRepository<Reserva, Long>{
    Reserva findByIdReserva(Long idReserva);
}
