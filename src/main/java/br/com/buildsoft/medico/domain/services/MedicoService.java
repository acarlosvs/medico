package br.com.buildsoft.medico.domain.services;

import br.com.buildsoft.medico.application.config.DataBaseConfig;
import br.com.buildsoft.medico.application.config.JMSConfig;
import br.com.buildsoft.medico.application.listener.MedicoProducer;
import br.com.buildsoft.medico.commons.dto.UsuarioMedicoDTO;
import br.com.buildsoft.medico.domain.dao.MedicoDao;
import br.com.buildsoft.medico.domain.models.Medico;

import java.sql.Connection;
import java.util.List;

import javax.jms.ConnectionFactory;

import com.google.gson.Gson;

public class MedicoService {

    public List<Medico> listar() {
        try (Connection connection = new DataBaseConfig().recuperarConexao()) {
            MedicoDao medicoDao = new MedicoDao(connection);

            return medicoDao.listarMedicos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public Medico salvar(Medico medico) {
        try (Connection connection = new DataBaseConfig().recuperarConexao()) {
            MedicoDao medicoDao = new MedicoDao(connection);
            
            medico = medicoDao.salvar(medico);
            
            Gson gson = new Gson();
            String json = gson.toJson(medico);
            
            ConnectionFactory connectionFactory = JMSConfig.createConnectionFactory();
            MedicoProducer medicoProducer = new MedicoProducer(connectionFactory);
            medicoProducer.sendMessage(JMSConfig.MEDICO_QEUE, json);
            
            return medico;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void cadastrarUsuarioMedico(UsuarioMedicoDTO usuarioMedicoDTO) {
    	System.out.println("Cadastrando usuário médico.");
    	
        try (Connection connection = new DataBaseConfig().recuperarConexao()) {
            MedicoDao medicoDao = new MedicoDao(connection);
            
            Medico medico = medicoDao.buscarPorId(usuarioMedicoDTO.getIdMedico());
            medico.setUsuarioId(usuarioMedicoDTO.getIdUsuario());

            medicoDao.inserirIdUsuario(medico);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
