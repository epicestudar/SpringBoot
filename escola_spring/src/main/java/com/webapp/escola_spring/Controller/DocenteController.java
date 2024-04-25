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
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;
import com.webapp.escola_spring.Model.Docente;
import com.webapp.escola_spring.Repository.AlunoRepository;
import com.webapp.escola_spring.Repository.DocenteRepository;

@Controller
public class DocenteController {
    @Autowired
    DocenteRepository alr;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AlunoRepository ar;

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
        session.invalidate();
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
                Docente docente = alr.findByEmailInstitucional(email);
                httpSession.setAttribute("docente", docente);
                httpSession.setAttribute("loggedin", true);
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

    @GetMapping("/gerenciamento-docente")
    public String listarDocentes(Model model) {
        List<Docente> docentes = (List<Docente>) alr.findAll();
        model.addAttribute("docentes", docentes);
        return "gerenciamento/gerenciamento-docente"; 
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
        return "redirect:/gerenciamento-docente"; 
    }

    @RequestMapping(value = "/edit-docente/{emailInstitucional}", method = RequestMethod.GET)
    public ModelAndView editarDocente(@PathVariable("emailInstitucional") String emailInstitucional) {
        ModelAndView mv = new ModelAndView("crud/docente/edit-docente");
        Docente docente = alr.findByEmailInstitucional(emailInstitucional);
        mv.addObject("emailInstitucional", emailInstitucional);
        mv.addObject("docente", docente);
        return mv;
    }

    @PostMapping("/atualizar-docente")
    public String atualizarDocente(@RequestParam("emailInstitucional") String emailInstitucional, Docente docente) {
        Docente docenteExistente = alr.findByEmailInstitucional(emailInstitucional);
        if (docenteExistente != null) {
            docenteExistente.setNome(docente.getNome());
            docenteExistente.setMateria(docente.getMateria());
            docenteExistente.setTurmas(docente.getTurmas());
            alr.save(docenteExistente);
            return "redirect:/gerenciamento-docente";
        } else {
            return "redirect:/gerenciamento-docente";
        }
    }
    @GetMapping("/filtro-alunos")
    public ModelAndView lancarNotas(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("crud/aluno/filtro-alunos");
        Docente docente = (Docente) session.getAttribute("docente");
        if (docente != null) {
           
        
        modelAndView.addObject("docente", docente);
        modelAndView.addObject("alunos", ar.findAll());
            
        } else {
            modelAndView.setViewName("redirect:/login-docente");
        }
        return modelAndView;
    }

    @GetMapping("/dados-docente")
    public ModelAndView dadosDocente(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("crud/docente/dados-docente");
        Docente docente = (Docente) session.getAttribute("docente");
        if (docente != null) {
           
        
        modelAndView.addObject("docente", docente);
        
            
        } else {
            modelAndView.setViewName("redirect:/login-docente");
        }
        return modelAndView;
    }
}