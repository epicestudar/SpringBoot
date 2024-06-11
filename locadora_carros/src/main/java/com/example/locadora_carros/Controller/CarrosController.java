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
import com.example.locadora_carros.Repository.CarrosRP;

@Controller
public class CarrosController {
    @Autowired
    private CarrosRP cr;

    @PostMapping("/cadastrar-carro")
    public String cadastrarCarroBD(Carros carro, Model model) {
        try {
            cr.save(carro);
            System.out.println("Cadastro realizado com sucesso!");
            return "/interna/interna-funcionario";
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar carro: " + e.getMessage());
            model.addAttribute("erroSenha", "Sua senha está incorreta");
            return "/cadastro/cadastro-carro";
        }
    }

    @GetMapping("/gerenciamento-carro")
    public String listarCarros(Model model) {
        List<Carros> carros = (List<Carros>) cr.findAll();
        model.addAttribute("carros", carros);
        return "gerenciamento/gerenciamento-carro";
    }

    @GetMapping("/reserva-carro")
    public String reservaCarros(Model model) {
        List<Carros> carros = (List<Carros>) cr.findAll();
        model.addAttribute("carros", carros);
        return "reserva/reserva-carro";
    }

    @RequestMapping(value = "/delete-carro/{idCarro}", method = RequestMethod.GET)
    public String excluirAluno(@PathVariable("idCarro") Long idCarro) {
        try {
            Carros carro = cr.findByIdCarro(idCarro);
            if (carro != null) {
                cr.delete(carro);
                System.out.println("Carro excluído com sucesso!");
            } else {
                System.out.println("Carro não encontrado para exclusão");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Carro: " + e.getMessage());
        }
        return "redirect:/gerenciamento-carro";
    }

    @RequestMapping(value = "/edit-carro/{idCarro}", method = RequestMethod.GET)
    public ModelAndView editarCarro(@PathVariable("idCarro") Long idCarro) {
        ModelAndView mv = new ModelAndView("crud/edit-carro");
        Carros carro = cr.findByIdCarro(idCarro);
        mv.addObject("idCarro", idCarro);
        mv.addObject("carro", carro);
        return mv;
    }

    @PostMapping("/atualizar-carro")
    public String atualizarCarro(@RequestParam("idCarro") Long idCarro, Carros carro) {
        Carros carroExistente = cr.findByIdCarro(idCarro);
        if (carroExistente != null) {
            carroExistente.setPlaca(carro.getPlaca());
            carroExistente.setModelo(carro.getModelo());
            carroExistente.setDisponibilidade(carro.getDisponibilidade());
            carroExistente.setTipo(carro.getTipo());
            cr.save(carroExistente);
            return "redirect:/gerenciamento-carro";
        } else {
            return "redirect:/gerenciamento-carro";
        }
    }

    @GetMapping("/buscar-carro")
    public String buscarCarroPorPlaca(@RequestParam(name = "placa", required = false) String placa, Model model) {
        if (placa != null && !placa.isEmpty()) {
            Carros carro = cr.findByPlaca(placa);
            if (carro != null) {
                model.addAttribute("carro", carro);
                model.addAttribute("mensagem", null); // Limpa a mensagem de erro, se houver
            } else {
                model.addAttribute("carro", null); // Limpa os dados do carro, se houver
                model.addAttribute("mensagem", "Carro não encontrado com a placa " + placa);
            }
        } else {
            model.addAttribute("carro", null); // Limpa os dados do carro, se houver
            model.addAttribute("mensagem", "Por favor, insira uma placa para buscar");
        }
        List<Carros> carros = (List<Carros>) cr.findAll(); // Lista todos os carros para exibição
        model.addAttribute("carros", carros);
        return "buscar/buscar-carro";
    }

}
