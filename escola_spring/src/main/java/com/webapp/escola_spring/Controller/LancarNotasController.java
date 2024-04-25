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
}
