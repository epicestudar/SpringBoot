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
public class Manutencao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idManutencao;
    private LocalDate dataManutencao;
    private String descricao;
    private double custo;

    @ManyToOne
    @JoinColumn(name = "idCarro", referencedColumnName = "idCarro")
    private Carros carro;

    // Getters e Setters
    public long getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(long idManutencao) {
        this.idManutencao = idManutencao;
    }

    public LocalDate getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(LocalDate dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public Carros getCarro() {
        return carro;
    }

    public void setCarro(Carros carro) {
        this.carro = carro;
    }
}
