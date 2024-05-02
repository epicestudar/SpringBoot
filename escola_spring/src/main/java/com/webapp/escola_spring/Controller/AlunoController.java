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

import com.webapp.escola_spring.Model.Aluno;
import com.webapp.escola_spring.Repository.AlunoRepository;
import com.webapp.escola_spring.Repository.DocenteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AlunoController {
    @Autowired
    AlunoRepository alr;

    @Autowired
    DocenteRepository ar;

    @Autowired
    private HttpSession httpSession;

    boolean acessoAluno = false;

    @PostMapping("/cadastrar-aluno")
    public String cadastrarAlunoBD(Aluno aluno, Model model) {
        try {
            alr.save(aluno);
            System.out.println("Cadastro realizado com sucesso!");
            return "/interna/interna-adm";
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
            model.addAttribute("erroSenha", "Sua senha está incorreta");
            return "/cadastro/cadastro-aluno_docente";
        }
    }

    @GetMapping("/logout-aluno")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login-aluno";
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
                Aluno aluno = alr.findByRa(ra);
                httpSession.setAttribute("aluno", aluno);
                httpSession.setAttribute("loggedin", true);
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

    @GetMapping("/gerenciamento-aluno")
    public String listarAlunos(Model model) {
        List<Aluno> alunos = (List<Aluno>) alr.findAll();
        model.addAttribute("alunos", alunos);
        return "gerenciamento/gerenciamento-aluno";
    }

    @RequestMapping(value = "/delete-aluno/{ra}", method = RequestMethod.GET)
    public String excluirAluno(@PathVariable("ra") String ra) {
        try {
            Aluno aluno = alr.findByRa(ra);
            if (aluno != null) {
                alr.delete(aluno);
                System.out.println("Aluno excluído com sucesso!");
            } else {
                System.out.println("Aluno não encontrado para exclusão");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Aluno: " + e.getMessage());
        }
        return "redirect:/gerenciamento-aluno";
    }

    @RequestMapping(value = "/edit-aluno/{ra}", method = RequestMethod.GET)
    public ModelAndView editarAluno(@PathVariable("ra") String ra) {
        ModelAndView mv = new ModelAndView("crud/aluno/edit-aluno");
        Aluno aluno = alr.findByRa(ra);
        mv.addObject("ra", ra);
        mv.addObject("aluno", aluno);
        return mv;
    }

    @PostMapping("/atualizar-aluno")
    public String atualizarAluno(@RequestParam("ra") String ra, Aluno aluno) {
        Aluno alunoExistente = alr.findByRa(ra);
        if (alunoExistente != null) {
            alunoExistente.setNome(aluno.getNome());
            alunoExistente.setCurso(aluno.getCurso());
            alr.save(alunoExistente);
            return "redirect:/gerenciamento-aluno";
        } else {
            return "redirect:/gerenciamento-aluno";
        }
    }
    @GetMapping("/interna-aluno/{ra}")
    public ModelAndView paginaAluno(@PathVariable("ra") String ra) {
        ModelAndView mv = new ModelAndView("interna/interna-aluno");
        Aluno aluno = alr.findByRa(ra);

        if (aluno != null) {
            mv.addObject("aluno", aluno);
        } else {
            
        }

        return mv;
    }

    @GetMapping("/filtro-docentes")
    public ModelAndView filtrarDocentes(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("crud/docente/filtro-docentes");
        Aluno aluno = (Aluno) session.getAttribute("aluno");
        if (aluno != null) {
           
        
        modelAndView.addObject("aluno", aluno);
        modelAndView.addObject("docentes", ar.findAll());
            
        } else {
            modelAndView.setViewName("redirect:/login-aluno");
        }
        return modelAndView;
    }

    @GetMapping("/dados-aluno")
    public ModelAndView dadosAluno(HttpSession session) {
    ModelAndView modelAndView = new ModelAndView("crud/aluno/dados-aluno");
    Aluno aluno = (Aluno) session.getAttribute("aluno");
    if (aluno != null) {
    modelAndView.addObject("aluno", aluno);

    } else {
    modelAndView.setViewName("redirect:/login-aluno");
    }
    return modelAndView;
    }

}
