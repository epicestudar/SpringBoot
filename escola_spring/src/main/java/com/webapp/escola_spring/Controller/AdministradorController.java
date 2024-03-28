package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.webapp.escola_spring.Model.Administrador;
import com.webapp.escola_spring.Repository.AdministradorRepository;
import com.webapp.escola_spring.Repository.VerificaCadastroAdmRepository;


@Controller
public class AdministradorController {

@Autowired
AdministradorRepository ar;

@Autowired
VerificaCadastroAdmRepository vcar;

@PostMapping("cadastrar-adm")
public String cadastrarAdmBD(Administrador adm) {
    boolean verificaCpf = vcar.existsById(adm.getCpf());
    if(verificaCpf) {
        ar.save(adm);
        System.out.println("Cadastro realizado com sucesso!");
    }
    else{
        System.out.println("Falha ao cadastrar o CPF");
    }

    return "/login/login-adm";
}

@PostMapping("acesso-adm")
public String postMethodName(@RequestBody String entity) {
    // método para verificar acesso
    
    return entity;
}

}
