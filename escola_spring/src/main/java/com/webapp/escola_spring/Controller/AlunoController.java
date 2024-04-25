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
import com.webapp.escola_spring.Model.Docente;
import com.webapp.escola_spring.Repository.AlunoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AlunoController {
    @Autowired
    AlunoRepository alr;

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
            model.addAttribute("erro", "Erro ao cadastrar aluno");
            return "/cadastro/cadastro-aluno_docente";
        }
    }

    @GetMapping("/logout-aluno")
    public String logout(HttpSession session) {
        // Invalida a sessão
        session.invalidate();
        // Redireciona para a página de login
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
        return "gerenciamento/gerenciamento-aluno"; // Nome da sua página HTML para listar docentes
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
        return "redirect:/gerenciamento-aluno"; // Redireciona de volta para a página de listar professores
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
            alunoExistente.setMateria(aluno.getMateria());
            alr.save(alunoExistente);
            return "redirect:/gerenciamento-aluno";
        } else {
            // Handle the case where the professor does not exist
            return "redirect:/gerenciamento-aluno";
        }
    }

    // @GetMapping("/filtro-aluno")
    // public ModelAndView filtroAluno() {
    // ModelAndView mv = new ModelAndView("aluno/filtrando-alunos");
    // mv.addObject("alunos", alr.findAll());
    // return mv;
    // }

    @GetMapping("/interna-aluno/{ra}")
    public ModelAndView paginaAluno(@PathVariable("ra") String ra) {
        ModelAndView mv = new ModelAndView("interna/interna-aluno");
        Aluno aluno = alr.findByRa(ra);

        if (aluno != null) {
            mv.addObject("aluno", aluno);
        } else {
            // Lógica para lidar com aluno não encontrado
        }

        return mv;
    }

    // @GetMapping("/aluno-filtrado")
    // public ModelAndView filtroAluno() {
    // ModelAndView mv = new ModelAndView("fragmentos/aluno-filtrado");
    // mv.addObject("alunos", alr.findAll());
    // return mv;
    // }

    @GetMapping("/dados-aluno")
    public ModelAndView dadosAluno(HttpSession session) {
    ModelAndView modelAndView = new ModelAndView("crud/aluno/dados-aluno");
    Aluno aluno = (Aluno) session.getAttribute("aluno");
    if (aluno != null) {
    modelAndView.addObject("aluno", aluno);

    } else {
    // Redirecionar para a página de login se o professor não estiver logado
    modelAndView.setViewName("redirect:/login-aluno");
    }
    return modelAndView;
    }

}
