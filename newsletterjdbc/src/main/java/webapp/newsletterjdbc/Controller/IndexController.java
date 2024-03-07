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
    public ModelAndView enviarDadosBanco(
            @RequestParam("email") String email,
            @RequestParam("nome") String nome,
            @RequestParam("telefone") String telefone,
            @RequestParam("cidade") String cidade) {
        
        ModelAndView mv = new ModelAndView("index");
        new IndexDAO().cadastrar(email, nome, telefone, cidade);
        return mv;
    }


}
