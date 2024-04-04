package com.webapp.escola_spring.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VerificaCadastroDocente implements Serializable{
    // atributos
    @Id
    private String emailInstitucional;
    private String senha;
    
    public String getEmailInstitucional() {
        return emailInstitucional;
    }
    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
