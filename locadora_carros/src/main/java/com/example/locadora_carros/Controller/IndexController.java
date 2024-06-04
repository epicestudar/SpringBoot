package com.example.locadora_carros.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/home")
    public String acessoHomePage() {
        return "index";
    }
    @GetMapping("/login-funcionario")
    public String acessoLoginFuncionario() {
        return "login/login-funcionario";
    }
    @GetMapping("/login-cliente")
    public String acessoLoginCliente() {
        return "login/login-cliente";
    }
    @GetMapping("/cadastro-funcionario")
    public String acessoCadastroFuncionario() {
        return "cadastro/cadastro-funcionario";
    }
    @GetMapping("/cadastro-cliente")
    public String acessoCadastroCliente() {
        return "cadastro/cadastro-cliente";
    }
    @GetMapping("/cadastro-carro")
    public String cadastroCarro() {
        return "cadastro/cadastro-carro";
    }
}
