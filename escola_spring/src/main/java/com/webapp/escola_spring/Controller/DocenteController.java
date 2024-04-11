package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webapp.escola_spring.Model.Docente;
import com.webapp.escola_spring.Repository.DocenteRepository;
import com.webapp.escola_spring.Repository.VerificaCadastroDocenteRepository;

@Controller
public class DocenteController {
    @Autowired
    DocenteRepository alr;

    @Autowired
    VerificaCadastroDocenteRepository vlcar;

    boolean acessoDocente = false;

    @PostMapping("cadastrar-docente")
    public String cadastrarDocenteBD(Docente docente, Model model) {
        boolean verificaEmail = vlcar.existsById(docente.getEmailInstitucional());
        if (verificaEmail) {
            alr.save(docente);
            System.out.println("Cadastro realizado com sucesso!");
            return "/login/login-docente";
        } else {
            System.out.println("Falha ao cadastrar o Email");
            model.addAttribute("erro", "Email não encontrado no banco");
            return "/cadastro/cadastro-adm";
        }
    }

    @GetMapping("/interna-docente")
    public String acessoPageInternaDocente() {
        String vaiPara = "";
        if (acessoDocente) {
            vaiPara = "interna/interna-docente";
        } else {
            vaiPara = "redirect:/login-docente";
        }
        return vaiPara;
    }

    @PostMapping("acesso-docente")
    public String acessoAluno(@RequestParam String email, @RequestParam String senha) {
        // método para verificar acesso
        try {
            boolean verificaEmail = alr.existsById(email);
            boolean verificaSenha = alr.findByEmailInstitucional(email).getSenha().equals(senha);
            String url = "";
            if (verificaEmail && verificaSenha) {
                acessoDocente = true;
                url = "redirect:/interna-docente";
            } else {
                url = "redirect:/login-docente";
            }
            return url;
        } catch (Exception e) {
            return "redirect:/login-docente";
        }
    }
}
