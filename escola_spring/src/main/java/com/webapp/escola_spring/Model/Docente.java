package com.webapp.escola_spring.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Docente implements Serializable{
    // atributos
    @Id
    private String emailInstitucional;
    private String nome;
    private String disciplinas;
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
    public String getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(String disciplinas) {
        this.disciplinas = disciplinas;
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
