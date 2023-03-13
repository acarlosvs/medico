package br.com.buildsoft.medico.application.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConfig {

	public DataBaseConfig() {
		
	}

	public Connection recuperarConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3307/imedicina";
			String usuario = "user";
			String senha = "password";

			return DriverManager.getConnection(url, usuario, senha);
		} catch(Exception ex){
			ex.printStackTrace();
		}

		return null;
	}
}
