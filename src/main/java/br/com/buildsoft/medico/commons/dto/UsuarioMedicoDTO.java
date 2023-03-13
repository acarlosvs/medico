package br.com.buildsoft.medico.commons.dto;

import java.math.BigInteger;

public class UsuarioMedicoDTO {

	private BigInteger idUsuario;
    private BigInteger idMedico;
    
    public UsuarioMedicoDTO() {
    	
    }

    public UsuarioMedicoDTO(BigInteger idUsuario, BigInteger idMedico) {
        this.idUsuario = idUsuario;
        this.idMedico = idMedico;
    }
    
    public BigInteger getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigInteger idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigInteger getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(BigInteger idMedico) {
        this.idMedico = idMedico;
    }
    
}
