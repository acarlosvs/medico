package br.com.buildsoft.medico.application.listener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MedicoProducer {
	
	private final ConnectionFactory connectionFactory;
	
	public MedicoProducer(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	public void sendMessage(String queueName, String messageText) {
		try {
			System.out.println("Enviando mensagem para fila: " + queueName);
			
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage(messageText);
			producer.send(message);
    		session.close();
    		connection.close();
		} catch (JMSException ex) {
            ex.printStackTrace();
        }
	}
}
