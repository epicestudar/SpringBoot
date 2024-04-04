package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.webapp.escola_spring.Model.Administrador;
import com.webapp.escola_spring.Repository.AdministradorRepository;
import com.webapp.escola_spring.Repository.VerificaCadastroAdmRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AdministradorController {

@Autowired
AdministradorRepository ar;

@Autowired
VerificaCadastroAdmRepository vcar;

boolean acessoAdm = false;

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

@GetMapping("/interna-adm")
public String acessoPageInternaAdm() {
    String vaiPara = "";
    if (acessoAdm) {
        vaiPara = "interna/interna-adm";
    } else{
        vaiPara = "redirect:/login-adm";
    }
    return vaiPara;
}

@PostMapping("acesso-adm")
public String acessoAdm(@RequestParam String cpf, @RequestParam String senha) {
    // método para verificar acesso
    try {
        boolean verificaCpf = ar.existsById(cpf);
        boolean verificaSenha = ar.findByCpf(cpf).getSenha().equals(senha);
        String url = "";
        if (verificaCpf && verificaSenha) {
            acessoAdm = true;
            url = "redirect:/interna-adm";
        } else{
            url = "redirect:/login-adm";
        }
        return url;
    } catch (Exception e) {
        return "redirect:/login-adm";
    }
}
}
