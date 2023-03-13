package br.com.buildsoft.medico.domain.dao;

import br.com.buildsoft.medico.domain.models.Medico;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicoDao {

    private final Connection connection;

    public MedicoDao(Connection connection) {
        this.connection = connection;
    }

    public List<Medico> listarMedicos() {
        List<Medico> medicos = new ArrayList<>();

        String sql = "SELECT id, nome, crm, usuario_id FROM medicos";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    medicos.add(new Medico(BigInteger.valueOf(rst.getLong(1)), rst.getString(2), rst.getString(3), BigInteger.valueOf(rst.getLong(4))));
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar lista de médicos. Erro: " + ex.getMessage());
        }

        return medicos;
    }
    
    public Medico salvar(Medico medico) {
        String sql = "INSERT INTO medicos(nome, crm) VALUES(?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, medico.getNome());
			pstm.setString(2, medico.getCrm());
			
            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    medico.setId(BigInteger.valueOf(rst.getLong(1)));
                }
            }
            
            return medico;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao cadastrar médico. Erro: " + ex.getMessage());
        }
    }
    
    public Medico buscarPorId(BigInteger id) {
    	System.out.println("Buscando usuário médico.");
    	
        Medico medico = new Medico();

        String sql = "SELECT id, nome, crm, usuario_id FROM medicos WHERE id = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
        	pstm.setLong(1, id.longValue());
        	
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    medico.setId(BigInteger.valueOf(rst.getLong(1)));
                    medico.setNome(rst.getString(2));
                    medico.setCrm(rst.getString(3));
                }
            }
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
            throw new RuntimeException("Erro ao buscar médico. Erro: " + ex.getMessage());
        }
        
        System.out.println("Usuário médico: " + medico.getNome());

        return medico;
    }
    
    public void inserirIdUsuario(Medico medico) {
    	System.out.println("Atualizando id do usuário médico.");
    	
        String sql = "UPDATE medicos SET usuario_id = ? WHERE id = ?";
        
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
        	pstm.setLong(1, medico.getUsuarioId().longValue());
            pstm.setLong(2, medico.getId().longValue());
            
            int linhasAfetadas = pstm.executeUpdate();
            
            System.out.println("Linhas afetadas: " + linhasAfetadas);
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
            throw new RuntimeException("Erro ao atualizar id de usuário do médico. Erro: " + ex.getMessage());
        }
    }
}
