package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.stereotype.Controller;
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
import com.webapp.escola_spring.Repository.LancarNotasRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LancarNotasController {
    @Autowired
    private AlunoRepository ar;

    @Autowired
    private LancarNotasRepository ln;

    @RequestMapping(value = "/lancando-nota/{ra}", method = RequestMethod.GET)
    public ModelAndView editarAluno(@PathVariable("ra") String ra) {
        ModelAndView mv = new ModelAndView("docente/lancando-nota");
        mv.addObject("aluno", ar.findByRa(ra));
        return mv;
    }

    // @PostMapping("/salvar-nota")
    // public String salvarNota(@RequestParam("materia") String materia,
    //         @RequestParam("nome") String nomeAluno,
    //         @RequestParam("nota") double nota, HttpSession session) {
    //             Aluno aluno = ar.findByNome(nomeAluno);
    //             Docente docente = (Docente) session.getAttribute("docente");

    //     if (aluno != null && docente != null && aluno.getTurma().equals(docente.getTurmas())) {
    //         LancarNotas lancamento = new LancarNotas();
    //         lancamento.setMateria(docente);
    //         lancamento.setNomeAluno(aluno);
    //         lancamento.setNota(nota);
    //         ln.save(lancamento);
    //         return "redirect:/lancamento"; // Redirecionar de volta para a página de lançamento
    //     } else {
    //         // Lógica para lidar com erros, por exemplo, exibir uma mensagem de erro
    //         return "redirect:/lancamento"; // Redirecionar de volta para a página de lançamento
    //     }
    // }

}
