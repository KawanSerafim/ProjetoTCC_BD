package controller.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.persistence.exceptions.OrientadorException;
import model.dao.OrientadorDAO;
import model.entidades.Orientador;

public class OrientadorDAOImpl implements OrientadorDAO {
	
	private Connection con = null;
	private static String HOST_NAME = "localhost"; //IP da maquina do servidor
	private static String DB_NAME = "clinica";// nome da database
	private static String USER = "sa";// nome do usario sqlserver
	private static String SENHA = "123456"; // senha do usuario
	
	public OrientadorDAOImpl() throws OrientadorException {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver"); // Referencia da lib de conexão
			//Configuração da String da conexão com SQLServer
			con = DriverManager.getConnection(
					String.format(
							"jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
							HOST_NAME,DB_NAME, USER, SENHA));
			
		}catch(ClassNotFoundException | SQLException err) {
			err.printStackTrace();
			throw new OrientadorException(err);
		}
	}
	
	
	@Override
	public void inserir(Orientador orientador) throws OrientadorException {
		try {
			//Primeiro insere a entidade mae, Pessoa
			inserirPessoa(orientador);
			String SQL = """
			          INSERT INTO orientador (pessoaId, matricula)
			          VALUES (?, ?)
			          """;
			      PreparedStatement stm = con.prepareStatement(SQL);
			      stm.setInt(1, 0);
			      stm.setString(2, orientador.getMatricula());
			      int i = stm.executeUpdate();
			      System.out.println(i);
			    } catch (SQLException er) {
			      er.printStackTrace();
			      throw new OrientadorException(er);
			    }
		
	}

	private void inserirPessoa(Orientador orientador) throws OrientadorException{
		try {
			String SQL = """
			          INSERT INTO pessoa (id, senha, nome, email)
			          VALUES (?, ?, ?)
			          """;
			      PreparedStatement stm = con.prepareStatement(SQL);
			      stm.setInt(1, 0);
			      stm.setString(2, orientador.getSenha());
			      stm.setString(3, orientador.getNome());
			      stm.setString(4, orientador.getEmail());
			      int i = stm.executeUpdate();
			      System.out.println(i);
			    } catch (SQLException er) {
			      er.printStackTrace();
			      throw new OrientadorException(er);
			    }
		
	}


	@Override
	public void atualizar(Orientador orientador) throws OrientadorException {
		try {
			 //Atualiza os dados da entidade mae
			  atualizarPessoa(orientador);
			  //Atualiza os dados de orientador
		      String SQL = """
		          UPDATE orientador SET matricula=?
		          WHERE id=?
		          """;
		      PreparedStatement stm = con.prepareStatement(SQL);
		      stm.setString(1, orientador.getMatricula());
		      stm.setInt(2, orientador.getId());
		      int i = stm.executeUpdate();
		      System.out.println(i);
		    } catch (SQLException er) {
		      er.printStackTrace();
		      throw new OrientadorException(er);
		    }
		
	}


	private void atualizarPessoa(Orientador orientador) throws OrientadorException {
		try {
		      String SQL = """
		          UPDATE pessoa SET senha=?, nome=?, email=?
		          WHERE id=?
		          """;
		      PreparedStatement stm = con.prepareStatement(SQL);
		      stm.setString(1, orientador.getSenha());
		      stm.setString(2, orientador.getNome());
		      stm.setString(3, orientador.getEmail());
		      stm.setInt(4, orientador.getId());
		      int i = stm.executeUpdate();
		      System.out.println(i);
		    } catch (SQLException er) {
		      er.printStackTrace();
		      throw new OrientadorException(er);
		    }
		
	}

}