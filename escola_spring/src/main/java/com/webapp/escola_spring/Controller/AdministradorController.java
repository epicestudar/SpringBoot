package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.webapp.escola_spring.Model.Administrador;
import com.webapp.escola_spring.Repository.AdministradorRepository;


@Controller
public class AdministradorController {

@Autowired
AdministradorRepository ar;

@PostMapping("cadastrar-adm")
public String cadastrarAdmBD(Administrador adm) {
    ar.save(adm);
    System.out.println("Cadastro realizado com sucesso!");
    return "/login/login-adm";
}

}
