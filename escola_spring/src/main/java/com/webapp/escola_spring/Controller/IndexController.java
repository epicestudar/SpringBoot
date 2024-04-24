package com.webapp.escola_spring.Controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.classic.Logger;


@Controller
public class IndexController {
    @GetMapping("/home")
    public String acessoHomePage() {
        return "index";
    }
    
    @GetMapping("/login-adm")
    public String acessoPageLoginAdm() {
        return "login/login-adm";
    }
    
    @GetMapping("/cadastro-adm")
    public String acessoPageCadastroAdm() {
        return "cadastro/cadastro-adm";
    }
    @GetMapping("/login-aluno")
    public String acessoPageLoginAluno() {
        return "login/login-aluno";
    }
    @GetMapping("/login-docente")
    public String acessoPageLoginDocente() {
        return "login/login-docente";
    }
    // @GetMapping("/cadastro-aluno_docente")
    // public String acessoPageCadastroAluno() {
    //     return "cadastro/cadastro-aluno_docente";
    // }

    @GetMapping("/cursos")
    public String acessoPageCursos() {
        return "cursos/cursos";
    }
    @GetMapping("/contato")
    public String acessoPageContato() {
        return "contato/contato";
    }

    @GetMapping("/lancamento")
    public String acessoPageNotas() {
        return "notas/lancamento";
    }
    // @GetMapping("/filtrando")
    // public String filtrandoAlunos() {
    //     return "crud/aluno/filtrando-alunos";
    // }
}
