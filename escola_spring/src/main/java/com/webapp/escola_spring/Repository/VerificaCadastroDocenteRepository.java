package com.webapp.escola_spring.Repository;

import org.springframework.data.repository.CrudRepository;
import com.webapp.escola_spring.Model.VerificaCadastroDocente;

public interface VerificaCadastroDocenteRepository extends CrudRepository<VerificaCadastroDocente, String>{
    VerificaCadastroDocente findByEmailInstitucional(String emailInstitucional);
}
