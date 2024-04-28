package com.webapp.escola_spring.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.escola_spring.Model.Aluno;
import com.webapp.escola_spring.Model.LancarFaltas;
import com.webapp.escola_spring.Repository.AlunoRepository;
import com.webapp.escola_spring.Repository.LancarFaltasRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class LancamentoFaltasController {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired LancarFaltasRepository lf;

    private static final Logger logger = LoggerFactory.getLogger(LancamentoNotasController.class);

    @PostMapping("/lancamento-falta")
    public String lancarFalta(@RequestParam("ra") String ra, @RequestParam("diaFalta") String diaFalta) {
        try {
            logger.info("Recebido pedido para lançar falta para o aluno com RA: {}", ra);
            logger.info("RA do aluno recebido: {}", ra);
            Aluno aluno = alunoRepository.findByRa(ra);

            if (aluno != null) {
                logger.info("Aluno encontrado: {}", aluno.getNome());
                LancarFaltas lancamentoFalta = new LancarFaltas();
                lancamentoFalta.setNomeAluno(aluno);
                lancamentoFalta.setDiaFalta(diaFalta);
                lf.save(lancamentoFalta);

                logger.info("Falta lançada com sucesso para o aluno: {}", aluno.getNome());
            } else {
                logger.error("Aluno não encontrado para lançamento de falta.");
            }
        } catch (Exception e) {
            logger.error("Erro ao lançar falta: {}", e.getMessage());
        }
        return "redirect:/filtro-alunos";
    }

    @GetMapping("/lancamento-falta")
    public String exibirFormularioNota(@RequestParam("ra") String ra, Model model) {
        try {
            Aluno aluno = alunoRepository.findByRa(ra);

            if (aluno != null) {
                model.addAttribute("aluno", aluno);
                return "faltas/lancamento";
            } else {
                return "redirect:/filtro-alunos";
            }
        } catch (Exception e) {
            return "redirect:/filtro-alunos";
        }
    }

    @GetMapping("/faltas-aluno")
    public ModelAndView visualizarNota(HttpSession session) {
        ModelAndView mv = new ModelAndView("crud/aluno/faltas-aluno");
    
        Aluno aluno = (Aluno) session.getAttribute("aluno");
        if (aluno != null) {
            List<LancarFaltas> faltasDoAluno = lf.findByNomeAluno(aluno);
            mv.addObject("aluno", aluno);
            mv.addObject("faltas", faltasDoAluno);
        } else {
            // Redirecionar para a página de login se o aluno não estiver logado
            mv.setViewName("redirect:/login-aluno");
        }
        return mv;
    }
}
