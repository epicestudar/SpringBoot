package com.example.locadora_carros.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.locadora_carros.Model.Cliente;
import com.example.locadora_carros.Repository.ClienteRP;

import jakarta.servlet.http.HttpSession;

@Controller
public class ClienteController {
    @Autowired
    private ClienteRP alr;

    // @Autowired
    // DocenteRepository ar;

    @Autowired
    private HttpSession httpSession;

    boolean acessoCliente = false;

    @PostMapping("/cadastrar-cliente")
    public String cadastrarClienteBD(Cliente cliente, Model model) {
        try {
            alr.save(cliente);
            System.out.println("Cadastro realizado com sucesso!");
            return "/login/login-cliente";
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            model.addAttribute("erroSenha", "Sua senha está incorreta");
            return "/cadastro/cadastro-cliente";
        }
    }

    @GetMapping("/logout-cliente")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login-cliente";
    }

    @GetMapping("/interna-cliente")
    public String acessoPageInternaCliente() {
        String vaiPara = "";
        if (acessoCliente) {
            vaiPara = "interna/interna-cliente";
        } else {
            vaiPara = "redirect:/login-cliente";
        }
        return vaiPara;
    }

    @PostMapping("acesso-cliente")
    public String acessoAluno(@RequestParam String email, @RequestParam String senha) {
        // método para verificar acesso
        try {
            boolean verificaEmail = alr.existsById(email);
            boolean verificaSenha = alr.findByEmail(email).getSenha().equals(senha);
            String url = "";
            if (verificaEmail && verificaSenha) {
                Cliente cliente = alr.findByEmail(email);
                httpSession.setAttribute("cliente", cliente);
                httpSession.setAttribute("loggedin", true);
                acessoCliente = true;
                url = "redirect:/interna-cliente";
            } else {
                url = "redirect:/login-cliente";
            }
            return url;
        } catch (Exception e) {
            return "redirect:/login-cliente";
        }
    }

    
}
