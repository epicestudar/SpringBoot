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

import jakarta.servlet.http.HttpSession;

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

    // @PostMapping("acesso-docente")
    // public String acessoDocente(@RequestParam String email, @RequestParam String
    // senha, HttpSession session) {
    // try {
    // Docente docente = alr.findByEmailInstitucional(email);
    // if (docente != null && docente.getSenha().equals(senha)) {
    // session.setAttribute("docente", docente);
    // return "redirect:/interna-docente";
    // } else {
    // return "redirect:/login-docente";
    // }
    // } catch (Exception e) {
    // return "redirect:/login-docente";
    // }
    // }

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
        return "gerenciamento/gerenciamento-docente"; // Nome da sua página HTML para listar docentes
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
        return "redirect:/gerenciamento-docente"; // Redireciona de volta para a página de listar professores
    }

    // @RequestMapping(value = "/edit-docente/{emailInstitucional}", method =
    // RequestMethod.GET)
    // public ModelAndView editarDocente(@PathVariable("emailInstitucional") String
    // emailInstitucional) {
    // ModelAndView mv = new ModelAndView("crud/docente/edit-docente");
    // mv.addObject("docente", alr.findByEmailInstitucional(emailInstitucional));
    // return mv;
    // }

    // @PostMapping("/atualizar-docente")
    // public String atualizarDocente(@ModelAttribute Docente docente) {
    // try {
    // // Verifica se o docente já existe no banco de dados
    // Optional<Docente> existingDocente =
    // alr.findById(docente.getEmailInstitucional());

    // if (existingDocente.isPresent()) {
    // // Atualiza os atributos do docente existente com os valores fornecidos pelo
    // // formulário
    // Docente updatedDocente = existingDocente.get();
    // updatedDocente.setNome(docente.getNome());
    // updatedDocente.setEmailInstitucional(docente.getEmailInstitucional());
    // updatedDocente.setMateria(docente.getMateria());
    // updatedDocente.setTurmas(docente.getTurmas());

    // // Salva o docente atualizado
    // alr.save(updatedDocente);
    // System.out.println("Docente atualizado com sucesso!");
    // } else {
    // System.out.println("Docente não encontrado para atualização");
    // }
    // } catch (Exception e) {
    // System.out.println("Erro ao atualizar docente: " + e.getMessage());
    // }
    // return "redirect:/gerenciamento"; // Redireciona de volta para a página de
    // listar professores
    // }

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
            // Handle the case where the professor does not exist
            return "redirect:/gerenciamento-docente";
        }
    }

    // @GetMapping("/lancamento")
    // public ModelAndView lancarNotas(HttpSession session) {
    // ModelAndView modelAndView = new ModelAndView("interna/interna-docente");
    // Docente docente = (Docente) session.getAttribute("docente");
    // if (docente != null) {

    // modelAndView.addObject("docente", docente);
    // modelAndView.addObject("alunos", ar.findAll());

    // } else {
    // // Redirecionar para a página de login se o professor não estiver logado
    // modelAndView.setViewName("redirect:/login-docente");
    // }
    // return modelAndView;
    // }
    @GetMapping("/filtrando")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    @GetMapping("/filtrando")
    public String filtrandoAlunos() {
        return "crud/aluno/filtrando-alunos";
    }
}