package com.example.locadora_carros.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.locadora_carros.Model.Funcionario;
import com.example.locadora_carros.Repository.FuncionarioRP;
import com.example.locadora_carros.Repository.VerificaFuncionarioRP;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRP fr;

    @Autowired
    private VerificaFuncionarioRP vfr;

    @Autowired
    private HttpSession httpSession;

    boolean acessoFuncionario = false;

    @PostMapping("/cadastrar-adm")
    public String cadastrarAlunoBD(Funcionario funcionario, Model model) {
        try {
            fr.save(funcionario);
            System.out.println("Cadastro realizado com sucesso!");
            return "/login/login-funcionario";
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
            model.addAttribute("erroSenha", "Sua senha está incorreta");
            return "/cadastro/cadastro-funcionario";
        }
    }
}
