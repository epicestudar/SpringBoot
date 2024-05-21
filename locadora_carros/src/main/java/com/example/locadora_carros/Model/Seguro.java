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
public class Seguro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idSeguro;
    private String seguradora;
    private String tipoSeguro;
    private String cobertura;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double valor;

    @ManyToOne
    @JoinColumn(name = "idCarro", referencedColumnName = "idCarro")
    private Carros carro;

    // Getters e Setters
    public long getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(long idSeguro) {
        this.idSeguro = idSeguro;
    }

    public String getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(String seguradora) {
        this.seguradora = seguradora;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Carros getCarro() {
        return carro;
    }

    public void setCarro(Carros carro) {
        this.carro = carro;
    }
}
