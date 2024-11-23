package controller.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.persistence.exceptions.SistemaException;
import model.dao.OrientadorDAO;
import model.entidades.Orientador;
import model.persistence.Conexao;

public class OrientadorDAOImpl implements OrientadorDAO {
	
	
	@Override
	public void inserir(Orientador orientador) throws SistemaException {
		try {
			//Primeiro insere a entidade mae, Pessoa
			inserirPessoa(orientador);
			String SQL = """
			          INSERT INTO orientador (pessoaId, matricula)
			          VALUES (?, ?)
			          """;
				 //Busca a instancia da conexao e instancia um java.sql.Connection
				  Connection con = Conexao.getInstancia().getConection();
			      PreparedStatement stm = con.prepareStatement(SQL);
			      stm.setInt(1, 0);
			      stm.setString(2, orientador.getMatricula());
			      int i = stm.executeUpdate();
			      System.out.println(i);
			      con.close(); 
			    } catch (SQLException er) {
			      er.printStackTrace();
			      throw new SistemaException(er);
			    }
		
	}

	private void inserirPessoa(Orientador orientador) throws SistemaException{
		try {
			String SQL = """
			          INSERT INTO pessoa (id, senha, nome, email)
			          VALUES (?, ?, ?)
			          """;
			      Connection con = Conexao.getInstancia().getConection();
			      PreparedStatement stm = con.prepareStatement(SQL);
			      stm.setInt(1, 0);
			      stm.setString(2, orientador.getSenha());
			      stm.setString(3, orientador.getNome());
			      stm.setString(4, orientador.getEmail());
			      int i = stm.executeUpdate();
			      System.out.println(i);
			      con.close();
			    } catch (SQLException er) {
			      er.printStackTrace();
			      throw new SistemaException(er);
			    }
		
	}


	@Override
	public void atualizar(Orientador orientador) throws SistemaException {
		try {
			 //Atualiza os dados da entidade mae
			  atualizarPessoa(orientador);
			  //Atualiza os dados de orientador
		      String SQL = """
		          UPDATE orientador SET matricula=?
		          WHERE id=?
		          """;
		      Connection con = Conexao.getInstancia().getConection();
		      PreparedStatement stm = con.prepareStatement(SQL);
		      stm.setString(1, orientador.getMatricula());
		      stm.setInt(2, orientador.getId());
		      int i = stm.executeUpdate();
		      System.out.println(i);
		      con.close();
		    } catch (SQLException er) {
		      er.printStackTrace();
		      throw new SistemaException(er);
		    }
		
	}


	private void atualizarPessoa(Orientador orientador) throws SistemaException {
		try {
		      String SQL = """
		          UPDATE pessoa SET senha=?, nome=?, email=?
		          WHERE id=?
		          """;
		      Connection con = Conexao.getInstancia().getConection();
		      PreparedStatement stm = con.prepareStatement(SQL);
		      stm.setString(1, orientador.getSenha());
		      stm.setString(2, orientador.getNome());
		      stm.setString(3, orientador.getEmail());
		      stm.setInt(4, orientador.getId());
		      int i = stm.executeUpdate();
		      System.out.println(i);
		      con.close();
		    } catch (SQLException er) {
		      er.printStackTrace();
		      throw new SistemaException(er);
		    }
		
	}

}