package com.webapp.escola_spring.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LancarFaltas implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idFalta;

    @ManyToOne
    @JoinColumn(name = "materias", referencedColumnName = "materia")
    private Docente materia;
    @ManyToOne
    @JoinColumn(name = "nomeAluno", referencedColumnName = "nome")
    private Aluno nomeAluno;
    private String diaFalta;
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public long getIdFalta() {
        return idFalta;
    }
    public void setIdFalta(long idFalta) {
        this.idFalta = idFalta;
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
    public String getDiaFalta() {
        return diaFalta;
    }
    public void setDiaFalta(String diaFalta) {
        this.diaFalta = diaFalta;
    }

    
}
