package com.example.locadora_carros.Model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Devolucao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idDevolucao;
    private LocalDate dataDevolucao;
    private String condicaoVeiculo;
    private double custosAdicionais;

    @ManyToOne
    @JoinColumn(name = "idCarro", referencedColumnName = "idCarro")
    private Carros carro;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;

    // Getters e Setters
    public long getIdDevolucao() {
        return idDevolucao;
    }

    public void setIdDevolucao(long idDevolucao) {
        this.idDevolucao = idDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getCondicaoVeiculo() {
        return condicaoVeiculo;
    }

    public void setCondicaoVeiculo(String condicaoVeiculo) {
        this.condicaoVeiculo = condicaoVeiculo;
    }

    public double getCustosAdicionais() {
        return custosAdicionais;
    }

    public void setCustosAdicionais(double custosAdicionais) {
        this.custosAdicionais = custosAdicionais;
    }

    public Carros getCarro() {
        return carro;
    }

    public void setCarro(Carros carro) {
        this.carro = carro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
