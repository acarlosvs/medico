package br.com.buildsoft.medico.application.listener;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.buildsoft.medico.application.config.JMSConfig;

public class JmsListener implements ServletContextListener {
	
	private UsuarioConsumer usuarioConsumer;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
        	System.out.println("Consumers JMS iniciados.");
        	
        	ConnectionFactory connectionFactory = JMSConfig.createConnectionFactory();
        	
        	usuarioConsumer = new UsuarioConsumer(connectionFactory);
        	usuarioConsumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	try {
    		System.out.println("Consumers JMS encerrado.");
    		
			usuarioConsumer.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }
}