package com.webapp.escola_spring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.webapp.escola_spring.Model.Docente;
import com.webapp.escola_spring.Repository.DocenteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class DocenteController {
    @Autowired
    DocenteRepository alr;

    boolean acessoDocente = false;

    @PostMapping("/cadastrar-docente")
    public String cadastrarAlunoBD(Docente docente, Model model) {
        try {
            alr.save(docente);
            System.out.println("Cadastro realizado com sucesso!");
            return "/interna/interna-adm";
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar docente: " + e.getMessage());
            model.addAttribute("erro", "Erro ao cadastrar docente");
            return "/cadastro/cadastro-aluno_docente";
        }
    }

    @GetMapping("/logout-docente")
    public String logout(HttpSession session) {
        // Invalida a sessão
        session.invalidate();
        // Redireciona para a página de login
        return "redirect:/login-docente";
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

    @GetMapping("/gerenciamento")
    public String listarDocentes(Model model) {
        List<Docente> docentes = (List<Docente>) alr.findAll();
        model.addAttribute("docentes", docentes);
        return "gerenciamento/gerenciamento-crud"; // Nome da sua página HTML para listar docentes
    }

    @RequestMapping(value = "/delete-docente/{email}", method = RequestMethod.GET)
    public String excluirProfessor(@PathVariable("email") String email) {
        try {
            Docente docente = alr.findByEmailInstitucional(email);
            if (docente != null) {
                alr.delete(docente);
                System.out.println("Professor excluído com sucesso!");
            } else {
                System.out.println("Professor não encontrado para exclusão");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir professor: " + e.getMessage());
        }
        return "redirect:/gerenciamento"; // Redireciona de volta para a página de listar professores
    }

}
