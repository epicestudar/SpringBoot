package com.example.locadora_carros.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.locadora_carros.Model.Funcionario;
import com.example.locadora_carros.Repository.FuncionarioRP;
import com.example.locadora_carros.Repository.VerificaFuncionarioRP;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRP fr;

    @Autowired
    private VerificaFuncionarioRP vfr;

    @Autowired
    private HttpSession httpSession;

    boolean acessoFuncionario = false;

    @PostMapping("/cadastrar-funcionario")
    public String cadastrarFuncionarioBD(Funcionario funcionario, Model model) {
        boolean verificaEmail = vfr.existsById(funcionario.getEmail());
        if(verificaEmail) {
            fr.save(funcionario);
            System.out.println("Cadastro realizado com sucesso!");
            return "/login/login-funcionario";
        }
        else {
            System.out.println("Falha ao cadastrar o Email");
            model.addAttribute("erro", "Email não encontrado no banco");
            return "/cadastro/cadastro-funcionario";
        }
    }

    @GetMapping("/logout-funcionario")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login-funcionario";
    }

    @GetMapping("/interna-funcionario")
    public String acessoPageInternaFuncionario() {
        String vaiPara = "";
        if (acessoFuncionario) {
            vaiPara = "interna/interna-funcionario";
        } else {
            vaiPara = "redirect:/login-funcionario";
        }
        return vaiPara;
    }

    @PostMapping("acesso-funcionario")
    public String acessoFuncionario(@RequestParam String email, @RequestParam String senha, Model model) {
        try {
            Funcionario funcionario = fr.findByEmail(email);
            if (funcionario != null) {
                boolean verificaSenha = funcionario.getSenha().equals(senha);
                String url = "";
                if (verificaSenha) {
                    httpSession.setAttribute("funcionario", funcionario);
                    httpSession.setAttribute("loggedin", true);
                    acessoFuncionario = true;
                    url = "redirect:/interna-funcionario";
                } else {
                    System.out.println("Falha ao autenticar funcionario");
                    model.addAttribute("erro", "Senha incorreta. Por favor, tente novamente.");
                    url = "redirect:/login-funcionario";
                }
                return url;
            } else {
                System.out.println("Falha ao autenticar funcionario");
                model.addAttribute("erro", "Usuário não encontrado. Por favor, verifique o CPF.");
                return "redirect:/login-funcionario";
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar o login: " + e.getMessage());
            return "redirect:/login-funcionario";
        }
    }

    @GetMapping("/dados-funcionario")
    public ModelAndView dadosFuncionario(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("crud/dados-funcionario");
        Funcionario funcionario = (Funcionario) session.getAttribute("funcionario");
        if (funcionario != null) {
           
        
        modelAndView.addObject("funcionario", funcionario);
        
            
        } else {
            modelAndView.setViewName("redirect:/login-funcionario");
        }
        return modelAndView;
    }

}
