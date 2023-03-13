package br.com.buildsoft.medico.domain.models;

import java.math.BigInteger;

public class Medico {
    private BigInteger id;
    private String nome;
    private String crm;
    private BigInteger usuarioId;
    
    public Medico() {
    	
    }

    public Medico(BigInteger id, String nome, String crm, BigInteger usuarioId) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.usuarioId = usuarioId;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
    
    public BigInteger getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(BigInteger usuarioId) {
        this.usuarioId = usuarioId;
    }
}
