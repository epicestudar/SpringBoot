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
            
            // Verifica se o RA está sendo passado corretamente
            logger.info("RA do aluno recebido: {}", ra);
            
            // Busca o aluno pelo RA
            Aluno aluno = alunoRepository.findByRa(ra);
            
            // Verifica se o aluno foi encontrado
            if (aluno != null) {
                logger.info("Aluno encontrado: {}", aluno.getNome());
                
                // Cria o lançamento de nota
                LancarNotas lancamento = new LancarNotas();
                lancamento.setNomeAluno(aluno);
                lancamento.setNota(nota);
                
                // Salva o lançamento de nota no banco de dados
                lancarNotasRepository.save(lancamento);
                
                logger.info("Nota lançada com sucesso para o aluno: {}", aluno.getNome());
            } else {
                logger.error("Aluno não encontrado para lançamento de nota.");
            }
        } catch (Exception e) {
            logger.error("Erro ao lançar nota: {}", e.getMessage());
        }
        return "redirect:/filtro-alunos"; // Redireciona de volta para a página de filtrar alunos
    }
}


