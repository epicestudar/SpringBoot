package webapp.newsletterjdbc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import webapp.newsletterjdbc.Connection.IndexDAO;

@Controller
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView abrirIndex() {
        ModelAndView mv = new ModelAndView("index");

        new IndexDAO().criaTabela();

        String mensagem = "Olá, seja bem-vinda(o)!";
        mv.addObject("msg", mensagem);

        return mv;
    }

@RequestMapping(value= "/", method=RequestMethod.POST)
public ModelAndView enviarEmailBanco(@RequestParam("email") String email) {
   ModelAndView mv = new ModelAndView("index");
   new IndexDAO().cadastrar(email);
    return mv;
}


}
