package br.com.epicestudar.apirest_senai.Model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class Responsavel implements Serializable {
    //atributos
    @Id
    private Long id;
    private String nome;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    
}