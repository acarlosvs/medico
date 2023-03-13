package br.com.buildsoft.medico.application.listener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.google.gson.Gson;

import br.com.buildsoft.medico.application.config.JMSConfig;
import br.com.buildsoft.medico.commons.dto.UsuarioMedicoDTO;
import br.com.buildsoft.medico.domain.services.MedicoService;

public class UsuarioConsumer implements MessageListener {

    private final ConnectionFactory connectionFactory;
    
    private Connection connection;
    private Session session;

    public UsuarioConsumer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void start() {
        try {
        	System.out.println("Medico consumer startado");
        	
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(JMSConfig.USUARIO_MEDICO_QEUE);
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(this);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

	@Override
	public void onMessage(Message message) {
		System.out.println("Consumindo mensagem usuário médico.");
		
        if (message instanceof TextMessage) {
            try {
            	System.out.println("Iniciando associação de usuário médico.");
            	
                String text = ((TextMessage) message).getText();
                
                Gson gson = new Gson();
                UsuarioMedicoDTO usuarioMedicoDTO = gson.fromJson(text, UsuarioMedicoDTO.class);
                
                MedicoService medicoService = new MedicoService();
                medicoService.cadastrarUsuarioMedico(usuarioMedicoDTO);
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
	}

	public void close() throws JMSException {
		session.close();
		connection.close();
	}
}
