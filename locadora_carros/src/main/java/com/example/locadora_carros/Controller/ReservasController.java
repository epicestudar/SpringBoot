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

import com.example.locadora_carros.Model.Carros;
import com.example.locadora_carros.Model.Cliente;
import com.example.locadora_carros.Model.Funcionario;
import com.example.locadora_carros.Model.Reserva;
import com.example.locadora_carros.Repository.ClienteRP;
import com.example.locadora_carros.Repository.ReservasRepository;

@Controller
public class ReservasController {
    @Autowired
    private ReservasRepository rp;

    @Autowired
    private ClienteRP cl;

    // @RequestMapping(value = "/reserva-carro", method = RequestMethod.GET)
    // public ModelAndView listarMateria() {
    // ModelAndView mv = new ModelAndView("reserva/reserva-carro");
    // mv.addObject("reservas", rp.findAll());
    // return mv;
    // }

    @PostMapping("/cadastrar-reserva")
    public String cadastrarReservaBD(Reserva reserva, Model model) {
        try {
            rp.save(reserva);
            System.out.println("Cadastro realizado com sucesso!");
            return "/interna/interna-cliente";

        } catch (Exception e) {
            System.out.println("Falha ao cadastrar a reserva");
            // model.addAttribute("erro", "Email não encontrado no banco");
            return "/reserva/reserva-carro";
        }
    }

    @GetMapping("/gerenciamento-reserva")
    public String listarReservas(Model model) {
        List<Reserva> reservas = (List<Reserva>) rp.findAll();
        model.addAttribute("reservas", reservas);
        return "gerenciamento/gerenciamento-reserva";
    }

    @RequestMapping(value = "/delete-reserva/{idReserva}", method = RequestMethod.GET)
    public String excluirReserva(@PathVariable("idReserva") Long idReserva) {
        try {
            Reserva reserva = rp.findByIdReserva(idReserva);
            if (reserva != null) {
                rp.delete(reserva);
                System.out.println("Reserva excluído com sucesso!");
            } else {
                System.out.println("Reserva não encontrado para exclusão");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Reserva: " + e.getMessage());
        }
        return "redirect:/gerenciamento-reserva";
    }

    @RequestMapping(value = "/edit-reserva/{idReserva}", method = RequestMethod.GET)
    public ModelAndView editarReserva(@PathVariable("idReserva") Long idReserva) {
        ModelAndView mv = new ModelAndView("crud/edit-reserva");
        Reserva reserva = rp.findByIdReserva(idReserva);
        mv.addObject("idReserva", idReserva);
        mv.addObject("reserva", reserva);
        mv.addObject("cliente", reserva.getCliente()); // Adicionando o objeto 'cliente' ao modelo
        return mv;
    }

    @PostMapping("/atualizar-reserva")
    public String atualizarReserva(@RequestParam("idReserva") Long idReserva, Reserva reserva) {
        Reserva reservaExistente = rp.findByIdReserva(idReserva);
        if (reservaExistente != null) {
            reservaExistente.setCliente(reserva.getCliente());
            reservaExistente.setIdReserva(reserva.getIdReserva());
            reservaExistente.setDataReserva(reserva.getDataReserva());
            reservaExistente.setDataDevolucao(reserva.getDataDevolucao());
            rp.save(reservaExistente);
            return "redirect:/gerenciamento-reserva";
        } else {
            return "redirect:/gerenciamento-reserva";
        }
    }
}
