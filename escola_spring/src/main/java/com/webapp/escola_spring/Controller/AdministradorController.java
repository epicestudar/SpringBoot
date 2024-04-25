package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.webapp.escola_spring.Model.Administrador;
import com.webapp.escola_spring.Repository.AdministradorRepository;
import com.webapp.escola_spring.Repository.VerificaCadastroAdmRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdministradorController {

    @Autowired
    AdministradorRepository ar;

    @Autowired
    VerificaCadastroAdmRepository vcar;

    @Autowired
    private HttpSession httpSession;

    boolean acessoAdm = false;

    @PostMapping("cadastrar-adm")
    public String cadastrarAdmBD(Administrador adm, Model model) {
        boolean verificaCpf = vcar.existsById(adm.getCpf());
        if (verificaCpf) {
            ar.save(adm);
            System.out.println("Cadastro realizado com sucesso!");
            return "/login/login-adm";
        } else {
            System.out.println("Falha ao cadastrar o CPF");
            model.addAttribute("erro", "CPF não encontrado no banco");
            return "/cadastro/cadastro-adm";
        }

    }

    @GetMapping("/logout-adm")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login-adm";
    }

    @GetMapping("/interna-adm")
    public String acessoPageInternaAdm() {
        String vaiPara = "";
        if (acessoAdm) {
            vaiPara = "interna/interna-adm";
        } else {
            vaiPara = "redirect:/login-adm";
        }
        return vaiPara;
    }

    @PostMapping("acesso-adm")
    public String acessoAdm(@RequestParam String cpf, @RequestParam String senha, Model model) {
        try {
            Administrador administrador = ar.findByCpf(cpf);
            if (administrador != null) {
                boolean verificaSenha = administrador.getSenha().equals(senha);
                String url = "";
                if (verificaSenha) {
                    httpSession.setAttribute("administrador", administrador);
                    httpSession.setAttribute("loggedin", true);
                    acessoAdm = true;
                    url = "redirect:/interna-adm";
                } else {
                    System.out.println("Falha ao autenticar adm");
                    model.addAttribute("erro", "Senha incorreta. Por favor, tente novamente.");
                    url = "redirect:/login-adm";
                }
                return url;
            } else {
                System.out.println("Falha ao autenticar adm");
                model.addAttribute("erro", "Usuário não encontrado. Por favor, verifique o CPF.");
                return "redirect:/login-adm";
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar o login: " + e.getMessage());
            return "redirect:/login-adm";
        }
    }

    @GetMapping("/dados-adm")
    public ModelAndView dadosAdm(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("crud/adm/dados-adm");
        Administrador administrador = (Administrador) session.getAttribute("administrador");
        if (administrador != null) {
           
        
        modelAndView.addObject("administrador", administrador);
        
            
        } else {
            modelAndView.setViewName("redirect:/login-adm");
        }
        return modelAndView;
    }

}
