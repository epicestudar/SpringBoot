package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.escola_spring.Model.Aluno;
import com.webapp.escola_spring.Model.LancarNotas;
import com.webapp.escola_spring.Repository.AlunoRepository;
import com.webapp.escola_spring.Repository.LancarNotasRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LancamentoNotasController {

    private static final Logger logger = LoggerFactory.getLogger(LancamentoNotasController.class);

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private LancarNotasRepository lancarNotasRepository;

    @PostMapping("/lancamento-nota")
    public String lancarNota(@RequestParam("ra") String ra, @RequestParam("nota") double nota) {
        try {
            logger.info("Recebido pedido para lançar nota para o aluno com RA: {}", ra);
            logger.info("RA do aluno recebido: {}", ra);
            Aluno aluno = alunoRepository.findByRa(ra);

            if (aluno != null) {
                logger.info("Aluno encontrado: {}", aluno.getNome());
                LancarNotas lancamento = new LancarNotas();
                lancamento.setNomeAluno(aluno);
                lancamento.setNota(nota);
                lancarNotasRepository.save(lancamento);

                logger.info("Nota lançada com sucesso para o aluno: {}", aluno.getNome());
            } else {
                logger.error("Aluno não encontrado para lançamento de nota.");
            }
        } catch (Exception e) {
            logger.error("Erro ao lançar nota: {}", e.getMessage());
        }
        return "redirect:/filtro-alunos";
    }

    @GetMapping("/lancamento-nota")
    public String exibirFormularioNota(@RequestParam("ra") String ra, Model model) {
        try {
            Aluno aluno = alunoRepository.findByRa(ra);

            if (aluno != null) {
                model.addAttribute("aluno", aluno);
                return "notas/lancamento";
            } else {
                return "redirect:/filtro-alunos";
            }
        } catch (Exception e) {
            return "redirect:/filtro-alunos";
        }
    }

    @GetMapping("/notas-aluno")
    public ModelAndView visualizarNota(HttpSession session) {
        ModelAndView mv = new ModelAndView("crud/aluno/notas-aluno");
    
        Aluno aluno = (Aluno) session.getAttribute("aluno");
        if (aluno != null) {
            List<LancarNotas> notasDoAluno = lancarNotasRepository.findByNomeAluno(aluno);
            mv.addObject("aluno", aluno);
            mv.addObject("notas", notasDoAluno);
        } else {
            // Redirecionar para a página de login se o aluno não estiver logado
            mv.setViewName("redirect:/login-aluno");
        }
        return mv;
    }
    

}