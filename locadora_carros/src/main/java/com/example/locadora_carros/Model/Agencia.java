package com.example.locadora_carros.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Agencia implements Serializable{
    @Id
    private long id_agencia;
    private int num_agencia;
    private String contato;
    private String endereco;
    private String cidade;
    private String estado;
    @ManyToOne
    @JoinColumn(name = "id_carro", referencedColumnName = "idCarro")
    private Carros carro;
    public long getId_agencia() {
        return id_agencia;
    }
    public void setId_agencia(long id_agencia) {
        this.id_agencia = id_agencia;
    }
    public int getNum_agencia() {
        return num_agencia;
    }
    public void setNum_agencia(int num_agencia) {
        this.num_agencia = num_agencia;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Carros getCarro() {
        return carro;
    }
    public void setCarro(Carros carro) {
        this.carro = carro;
    }
    
}
