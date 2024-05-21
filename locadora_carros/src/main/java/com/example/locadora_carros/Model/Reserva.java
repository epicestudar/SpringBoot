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
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idReserva;
    private LocalDate dataReserva;
    private LocalDate dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "placa", referencedColumnName = "placa")
    private Carros carro;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;

    // Getters e Setters
    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
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
