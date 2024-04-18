package com.webapp.escola_spring.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Docente implements Serializable{
    // atributos
    @Id
    private String emailInstitucional;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_materias", referencedColumnName = "idMateria")
    private Materias materias;
    private String materia;
    private String turmas;
    private String senha;
    public String getEmailInstitucional() {
        return emailInstitucional;
    }
    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Materias getMaterias() {
        return materias;
    }
    public void setMaterias(Materias materias) {
        this.materias = materias;
    }
    public String getMateria() {
        return materia;
    }
    public void setMateria(String materia) {
        this.materia = materia;
    }
    public String getTurmas() {
        return turmas;
    }
    public void setTurmas(String turmas) {
        this.turmas = turmas;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    

    
}