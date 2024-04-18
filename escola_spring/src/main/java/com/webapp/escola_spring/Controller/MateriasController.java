package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.escola_spring.Repository.MateriasRepository;

@Controller
public class MateriasController {
    @Autowired
    private MateriasRepository mr;

    @RequestMapping(value = "/cadastro-aluno_docente", method = RequestMethod.GET)
    public ModelAndView listarMateria() {
        ModelAndView mv = new ModelAndView("cadastro/cadastro-aluno_docente");
        mv.addObject("materias", mr.findAll());
        return mv;
    }
}
