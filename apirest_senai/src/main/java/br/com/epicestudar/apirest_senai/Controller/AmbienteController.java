package br.com.epicestudar.apirest_senai.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.epicestudar.apirest_senai.Model.Ambiente;
import br.com.epicestudar.apirest_senai.Repository.AmbienteRepository;

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
@RequestMapping("/ambiente")
public class AmbienteController {
    
    @Autowired
    AmbienteRepository repository;

    @GetMapping()
    public List<Ambiente> getAmbiente () {
        return (List<Ambiente>) repository.findAll();
    }
    
    @PostMapping()
    public Ambiente postAmbiente(@RequestBody Ambiente ambiente) {
        return repository.save(ambiente);
    }

    @GetMapping("/{id}")
    public Optional<Ambiente> getAmbienteById(@PathVariable Long id) {
        return repository.findById(id);
    }
    @PutMapping("/{id}")
    public Ambiente putAmbiente(  @PathVariable Long id, 
                                        @RequestBody Ambiente ambiente) {
        Optional<Ambiente> busca = repository.findById(id);
        if(!busca.isEmpty()){
            ambiente.setId(id);
            return repository.save(ambiente);
        }else{
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public void deleteAmbiente(@PathVariable Long id){
        repository.deleteById(id);      
    }
    
    
    
}