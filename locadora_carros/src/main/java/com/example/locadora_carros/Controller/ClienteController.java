package com.example.locadora_carros.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/dados-cliente")
    public ModelAndView dadosCliente(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("crud/dados-cliente");
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente != null) {
           
        
        modelAndView.addObject("cliente", cliente);
        
            
        } else {
            modelAndView.setViewName("redirect:/login-cliente");
        }
        return modelAndView;
    }

    @GetMapping("/gerenciamento-cliente")
    public String listarClientes(Model model) {
        List<Cliente> clientes = (List<Cliente>) alr.findAll();
        model.addAttribute("clientes", clientes);
        return "gerenciamento/gerenciamento-cliente";
    }

    @RequestMapping(value = "/delete-cliente/{email}", method = RequestMethod.GET)
    public String excluirAluno(@PathVariable("email") String email) {
        try {
            Cliente cliente = alr.findByEmail(email);
            if (cliente != null) {
                alr.delete(cliente);
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Cliente não encontrado para exclusão");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Cliente: " + e.getMessage());
        }
        return "redirect:/gerenciamento-cliente";
    }

    @RequestMapping(value = "/edit-cliente/{email}", method = RequestMethod.GET)
    public ModelAndView editarCarro(@PathVariable("email") String email) {
        ModelAndView mv = new ModelAndView("crud/edit-cliente");
        Cliente cliente = alr.findByEmail(email);
        mv.addObject("email", email);
        mv.addObject("cliente", cliente);
        return mv;
    }

    @PostMapping("/atualizar-cliente")
    public String atualizarCarro(@RequestParam("email") String email, Cliente cliente) {
        Cliente clienteExistente = alr.findByEmail(email);
        if (clienteExistente != null) {
            clienteExistente.setEmail(cliente.getEmail());
            clienteExistente.setNome(cliente.getNome());
            clienteExistente.setEstado(cliente.getEstado());
            clienteExistente.setTelefone(cliente.getTelefone());
            alr.save(clienteExistente);
            return "redirect:/gerenciamento-cliente";
        } else {
            return "redirect:/gerenciamento-cliente";
        }
    }

    
}
