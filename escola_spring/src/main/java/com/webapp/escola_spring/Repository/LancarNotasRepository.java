package com.webapp.escola_spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_spring.Model.LancarNotas;

public interface LancarNotasRepository extends JpaRepository<LancarNotas, Long>{

}