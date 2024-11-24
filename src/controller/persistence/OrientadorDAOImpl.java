package controller.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.persistence.exceptions.SistemaException;
import model.dao.OrientadorDAO;
import model.dao.PessoaDAO;
import model.entidades.Orientador;
import model.persistence.Conexao;

public class OrientadorDAOImpl implements OrientadorDAO {
	
	
	@Override
	public void inserir(Orientador orientador) throws SistemaException {
		if(verificaPessoaOrientadorExiste(orientador) == false) {
			try {
				//Primeiro insere a entidade mae, Pessoa
				inserirPessoa(orientador);
				String SQL = """
				          INSERT INTO orientador (pessoaId, matricula)
				          VALUES (?, ?)
				          """;
					 //Busca a instancia da conexao e instancia um java.sql.Connection
					  Connection con = Conexao.getInstancia().getConnection();
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
		} else {
			throw new SistemaException(new Exception("Orientador ou pessoa já existe no sistema"));
		}
	}

	public boolean verificaPessoaOrientadorExiste(Orientador orientador) throws SistemaException {
		//Select condiconal e com junção de tabelas
		//Verifica se existe um orientador igual ao objeto recebido como parametro
		List<Orientador> lista = new ArrayList<>();
		try {
			String SQL = """
				SELECT p.id, p.nome, p.senha, p.email,
				o.matricula FROM pessoa p
				INNER JOIN orientador o
				ON p.id = o.pessoaId
				WHERE o.pessoaId=?
				""";
			Connection con = Conexao.getInstancia().getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, orientador.getId());
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Orientador o = new Orientador();
				o.setId(rs.getInt("id"));
				o.setNome(rs.getString("nome"));
				o.setEmail(rs.getString("email"));
				o.setSenha(rs.getString("senha"));
				o.setMatricula(rs.getString("matricula"));
				lista.add(o);
			}
		} catch (SQLException e) {
			throw new SistemaException(e);
		}
		//compara valores
		for(Orientador item : lista ) {
			if(item.equals(orientador)) {
				return true;
			}
		}
		return false;
	}

	private void inserirPessoa(Orientador orientador) throws SistemaException{
			try {
				//verifica se ja existe pessoa com o mesmo email
				
				String SQL = """
				          INSERT INTO pessoa (id, senha, nome, email)
				          VALUES (?, ?, ?)
				          """;
				      Connection con = Conexao.getInstancia().getConnection();
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
		      Connection con = Conexao.getInstancia().getConnection();
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
		      Connection con = Conexao.getInstancia().getConnection();
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



	@Override
	public Orientador buscarOrientadorPorEmail(String email) throws SistemaException {
		List<Orientador> lista = new ArrayList<>();
		try {
			//Select condiconal e com junção de tabelas
			String SQL = """
				SELECT p.id, p.nome, p.email, p.senha,
				o.matricula FROM pessoa p
				INNER JOIN orientador o
				ON p.id = o.pessoaId
				WHERE p.email = ?
				""";
			Connection con = Conexao.getInstancia().getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Orientador o = new Orientador();
				o.setId(rs.getInt("id"));
				o.setNome(rs.getString("nome"));
				o.setEmail(rs.getString("email"));
				o.setSenha(rs.getString("senha"));
				o.setMatricula(rs.getString("matricula"));
				lista.add(o);
			}
		} catch (SQLException e) {
			throw new SistemaException(e);
		}
		return lista.getFirst();
	}

}