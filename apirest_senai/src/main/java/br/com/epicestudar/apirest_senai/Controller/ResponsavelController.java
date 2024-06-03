package br.com.epicestudar.apirest_senai.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.epicestudar.apirest_senai.Model.Responsavel;
import br.com.epicestudar.apirest_senai.Repository.ReponsavelRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {
    
    @Autowired
    ReponsavelRepository repository;

    @GetMapping()
    public List<Responsavel> getResponsavel () {
        return (List<Responsavel>) repository.findAll();
    }
    
    @PostMapping()
    public Responsavel postResponsavel(@RequestBody Responsavel responsavel) {
        return repository.save(responsavel);
    }

    @GetMapping("/{id}")
    public Optional<Responsavel> getResponsavelById(@PathVariable Long id) {
        return repository.findById(id);
    }
    @PutMapping("/{id}")
    public Responsavel putResponsavel(  @PathVariable Long id, 
                                        @RequestBody Responsavel responsavel) {
        Optional<Responsavel> busca = repository.findById(id);
        if(!busca.isEmpty()){
            responsavel.setId(id);
            return repository.save(responsavel);
        }else{
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public void deleteResponsavel(@PathVariable Long id){
        repository.deleteById(id);      
    }
    
    
    
}