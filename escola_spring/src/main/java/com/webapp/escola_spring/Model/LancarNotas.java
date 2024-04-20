package com.webapp.escola_spring.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LancarNotas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_nota;
    @ManyToOne
    @JoinColumn(name = "materias", referencedColumnName = "materia")
    private Docente materia;
    @ManyToOne
    @JoinColumn(name = "nomeAluno", referencedColumnName = "nome")
    private Aluno nomeAluno;
    private double nota;
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public long getId_nota() {
        return id_nota;
    }
    public void setId_nota(long id_nota) {
        this.id_nota = id_nota;
    }
    public Docente getMateria() {
        return materia;
    }
    public void setMateria(Docente materia) {
        this.materia = materia;
    }
    public Aluno getNomeAluno() {
        return nomeAluno;
    }
    public void setNomeAluno(Aluno nomeAluno) {
        this.nomeAluno = nomeAluno;
    }
    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }

    
}
