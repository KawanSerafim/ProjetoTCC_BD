package model.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controller.persistence.exceptions.SistemaException;

public class Conexao {
	
	private static String HOST_NAME = "localhost"; //IP da maquina do servidor
	private static String DB_NAME = "projeto_tcc";// nome da database
	private static String USER = "sa";// nome do usario sqlserver
	private static String SENHA = "123456"; // senha do usuario
	private static String DB_URL = String.format(
			"jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
			HOST_NAME,DB_NAME, USER, SENHA); 
	
	private static Conexao instancia;
	private Connection connection;

	
	//Construtor privado para garantir que apenas 1 Connection seja feita, reaproveitada e fechada
	private Conexao() throws SistemaException {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver"); // Referencia da lib de conexão
		} catch (ClassNotFoundException e) {
			throw new SistemaException(e);
		}
	}
	
	public static Conexao getInstancia() throws SistemaException {
		if (instancia == null) {
			instancia = new Conexao();
		}
		return instancia;
	}
	
	public Connection getConnection() throws SistemaException {
		try {
			if (this.connection == null || 
	                this.connection.isClosed()) {
				//Configuração da String da conexão com SQLServer
						this.connection = DriverManager.getConnection(DB_URL);
			}
		} catch (SQLException e) {
			throw new SistemaException(e);
		}
		return this.connection;
	}
}
