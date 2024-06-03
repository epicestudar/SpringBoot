package br.com.epicestudar.apirest_senai.Model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class AtivoPatrimonial implements Serializable {
    //atributos
    @Id
    private Long id;
    
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_ambiente")
    private Ambiente ambiente;

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

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    
}