package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.escola_spring.Model.Aluno;
import com.webapp.escola_spring.Repository.AlunoRepository;

@Controller
public class AlunoController {
    @Autowired
    AlunoRepository alr;

    boolean acessoAluno = false;

    @PostMapping("/cadastrar-aluno")
    public String cadastrarAlunoBD(Aluno aluno, Model model) {
        try {
            alr.save(aluno);
            System.out.println("Cadastro realizado com sucesso!");
            return "/interna/interna-adm";
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
            model.addAttribute("erro", "Erro ao cadastrar aluno");
            return "/cadastro/cadastro-aluno";
        }
    }

    @GetMapping("/interna-aluno")
    public String acessoPageInternaAluno() {
        String vaiPara = "";
        if (acessoAluno) {
            vaiPara = "interna/interna-aluno";
        } else {
            vaiPara = "redirect:/login-aluno";
        }
        return vaiPara;
    }

    @PostMapping("acesso-aluno")
    public String acessoAluno(@RequestParam String ra, @RequestParam String senha) {
        // método para verificar acesso
        try {
            boolean verificaRa = alr.existsById(ra);
            boolean verificaSenha = alr.findByRa(ra).getSenha().equals(senha);
            String url = "";
            if (verificaRa && verificaSenha) {
                acessoAluno = true;
                url = "redirect:/interna-aluno";
            } else {
                url = "redirect:/login-aluno";
            }
            return url;
        } catch (Exception e) {
            return "redirect:/login-aluno";
        }
    }
}
