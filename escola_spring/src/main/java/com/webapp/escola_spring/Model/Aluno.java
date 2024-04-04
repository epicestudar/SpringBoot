package com.webapp.escola_spring.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Aluno implements Serializable{
    // atributos
    @Id
    private String ra;
    private String nome;
    private String curso;
    private String disciplinas;
    private String periodo;
    private String senha;
    
    public String getRa() {
        return ra;
    }
    public void setRa(String ra) {
        this.ra = ra;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public String getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(String disciplinas) {
        this.disciplinas = disciplinas;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
