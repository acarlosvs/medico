package br.com.buildsoft.medico.application.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConfig {
    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    
    public static final String MEDICO_QEUE = "medico";
    public static final String USUARIO_MEDICO_QEUE = "usuario_medico";
    
    public static ConnectionFactory createConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connectionFactory.setUserName(USERNAME);
        connectionFactory.setPassword(PASSWORD);
        return connectionFactory;
    }
}
