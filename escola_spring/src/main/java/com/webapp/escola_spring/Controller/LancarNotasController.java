package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.webapp.escola_spring.Repository.AlunoRepository;
import com.webapp.escola_spring.Repository.LancarNotasRepository;

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

}
