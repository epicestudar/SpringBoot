package webapp.rh_app_jpa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webapp.rh_app_jpa.Model.Funcionario;
import webapp.rh_app_jpa.Repository.FuncionarioRepository;

@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository fr;

    @RequestMapping(value = "/funcionario", method = RequestMethod.GET)
    public String abrirfuncionario() {
        return "funcionario/funcionario";
    }

    @RequestMapping(value = "/funcionario", method = RequestMethod.POST)
    public String gravarfuncionario(Funcionario funcionario) {
        fr.save(funcionario);
        return "redirect:/funcionario";
    }
}
