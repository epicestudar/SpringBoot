package com.webapp.escola_spring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.escola_spring.Model.Aluno;
import com.webapp.escola_spring.Model.Docente;
import com.webapp.escola_spring.Model.LancarNotas;
import com.webapp.escola_spring.Repository.AlunoRepository;
import com.webapp.escola_spring.Repository.DocenteRepository;
import com.webapp.escola_spring.Repository.LancarNotasRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LancarNotasController {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private LancarNotasRepository lancarNotasRepository;

    // public ModelAndView lancarNotas(HttpSession session) {
    //     ModelAndView modelAndView = new ModelAndView("crud/docente/lancamento");
    //     Docente docente = (Docente) session.getAttribute("docente");

    //     if (docente != null) {
    //         List<Aluno> alunos = alunoRepository.findByMaterias(docente.getMaterias());
    //         modelAndView.addObject("alunos", alunos);
            
    //     } else {
    //         // modelAndView.setViewName("redirect:/login-docente");
    //     }

    //     return modelAndView;
    // }

    // @PostMapping("/lancamento")
    // public String lancarNotas(@RequestParam("ra") String ra, @RequestParam("nota") double nota) {
    //     try {
    //         Aluno aluno = alunoRepository.findByRa(ra);
    //         if (aluno != null) {
    //             LancarNotas lancarNotas = new LancarNotas();
    //             lancarNotas.setNomeAluno(aluno);
    //             lancarNotas.setNota(nota);
    //             lancarNotasRepository.save(lancarNotas);
    //             return "redirect:/lancamento";
    //         } else {
    //             // Trate o caso em que o aluno não é encontrado
    //             return "redirect:/lancamento";
    //         }
    //     } catch (Exception e) {
    //         // Trate qualquer exceção que possa ocorrer
    //         return "redirect:/lancamento";
    //     }
    // }
}
