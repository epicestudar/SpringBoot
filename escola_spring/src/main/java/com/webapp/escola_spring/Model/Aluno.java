package com.webapp.escola_spring.Model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Aluno implements Serializable {
    // atributos
    @Id
    private String ra;
    private String nome;
    private String curso;
    @ManyToOne
    @JoinColumn(name = "id_materias", referencedColumnName = "idMateria")
    private Materias materias;
    private String materia;
    @OneToMany(mappedBy = "nomeAluno")
    private List<LancarNotas> lancamentosNotas;

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
    public List<LancarNotas> getLancamentosNotas() {
        return lancamentosNotas;
    }

    public void setLancamentosNotas(List<LancarNotas> lancamentosNotas) {
        this.lancamentosNotas = lancamentosNotas;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    private String turma;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
