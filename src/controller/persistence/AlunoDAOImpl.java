package controller.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.persistence.exceptions.SistemaException;
import model.dao.AlunoDAO;
import model.dao.PessoaDAO;
import model.entidades.Aluno;
import model.entidades.Pessoa;
import model.persistence.Conexao;

public class AlunoDAOImpl implements AlunoDAO, PessoaDAO {

	@Override
	public void inserir(Aluno a) throws SistemaException {
		try {
			//Primeiro insere a entidade mae, Pessoa
			inserirPessoa(a);
			String SQL = """
			          INSERT INTO aluno (pessoaId, grupoId, ra, turno, semestre, curso)
			          VALUES (?, ?, ?, ?,?, ?)
			          """;
				 //Busca a instancia da conexao e instancia um java.sql.Connection
				  Connection con = Conexao.getInstancia().getConnection();
			      PreparedStatement stm = con.prepareStatement(SQL);
			      stm.setInt(1, 0);
			      stm.setString(2, "null");
			      stm.setString(3, a.getRa());
			      stm.setString(4, a.getTurno());
			      stm.setString(5, String.valueOf(a.getSemestre()));
			      stm.setString(6, a.getCurso());
			      
			      int i = stm.executeUpdate();
			      System.out.println(i);
			      con.close(); 
			    } catch (SQLException er) {
			      er.printStackTrace();
			      throw new SistemaException(er);
			    }

	}

	private void inserirPessoa(Aluno a) throws SistemaException {
		if (buscaIdPessoaPorEmail(a.getEmail()) == null) {
			try {
				//Verifica se ja existe pessoa com esse email
				String SQL = """
				          INSERT INTO pessoa (id, senha, nome, email)
				          VALUES (?, ?, ?)
				          """;
				      Connection con = Conexao.getInstancia().getConnection();
				      PreparedStatement stm = con.prepareStatement(SQL);
				      stm.setInt(1, 0);
				      stm.setString(2, a.getSenha());
				      stm.setString(3, a.getNome());
				      stm.setString(4, a.getEmail());
				      int i = stm.executeUpdate();
				      System.out.println(i);
				      con.close();
		    } catch (SQLException er) {
		      er.printStackTrace();
		      throw new SistemaException(er);
		    }
		} else {
			SQLException er = new SQLException();
			throw new SistemaException(er);
		}
		
	}
	
	@Override
	public Integer buscaIdPessoaPorEmail(String email) throws SistemaException {
		Integer id = null;
		try {
			//Select condicIonal
			String SQL = """
				SELECT id FROM pessoa 
				WHERE email = ?
				""";
			Connection con = Conexao.getInstancia().getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			throw new SistemaException(e);
		}
		return id;
	}
	

	@Override
	public void atualizar(Aluno a) throws SistemaException {
		try {
			 //Atualiza os dados da entidade mae
			  atualizarPessoa(a);
			  //Atualiza os dados de orientador
		      String SQL = """
		          UPDATE aluno SET ra=?, turno=?, semestre=?, curso=?
		          WHERE pessoaId=?
		          """;
		      Connection con = Conexao.getInstancia().getConnection();
		      PreparedStatement stm = con.prepareStatement(SQL);
		      stm.setString(1, a.getRa());
		      stm.setString(2, a.getTurno());
		      stm.setString(3, String.valueOf(a.getSemestre()) );
		      stm.setString(4, a.getCurso());
		      stm.setInt(5, a.getId());
		      
		      int i = stm.executeUpdate();
		      System.out.println(i);
		      con.close();
		    } catch (SQLException er) {
		      er.printStackTrace();
		      throw new SistemaException(er);
		    }

	}

	private void atualizarPessoa(Aluno a) throws SistemaException {
		try {
		      String SQL = """
		          UPDATE pessoa SET senha=?, nome=?, email=?
		          WHERE id=?
		          """;
		      Connection con = Conexao.getInstancia().getConnection();
		      PreparedStatement stm = con.prepareStatement(SQL);
		      stm.setString(1, a.getSenha());
		      stm.setString(2, a.getNome());
		      stm.setString(3, a.getEmail());
		      stm.setInt(4, a.getId());
		      int i = stm.executeUpdate();
		      System.out.println(i);
		      con.close();
		    } catch (SQLException er) {
		      er.printStackTrace();
		      throw new SistemaException(er);
		    }
		
	}

	@Override
	public void remover(Aluno a) throws SistemaException {
		//remove primeiro entidade filha
		try {
			String SQL = """
				DELETE FROM aluno WHERE pessoaId=?
				""";
			Connection con = Conexao.getInstancia().getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, a.getId());
			System.out.println(stm.executeUpdate());
			//remove a entidade mae
			removerPessoa(a);
		} catch (SQLException er) {
			er.printStackTrace();
			throw new SistemaException(er);
		}
		

	}

	private void removerPessoa(Aluno a) throws SistemaException {
		//remove primeiro entidade filha
				try {
					String SQL = """
						DELETE FROM pessoa WHERE id=?
						""";
					Connection con = Conexao.getInstancia().getConnection();
					PreparedStatement stm = con.prepareStatement(SQL);
					stm.setInt(1, a.getId());
					System.out.println(stm.executeUpdate());
					//remove a entidade mae
					removerPessoa(a);
				} catch (SQLException er) {
					er.printStackTrace();
					throw new SistemaException(er);
				}
	}

	@Override
	public boolean verificaLoginESenha(String email, String senha) throws SistemaException {
		try {
			//Select condicIonal para verificar login
			String SQL = """
				SELECT email, senha FROM pessoa p
				WHERE email=? AND senha=?
				""";
			Connection con = Conexao.getInstancia().getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, email);
			stm.setString(2,senha);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				if(rs.getString("email").equals(email)) {
					if(rs.getString("senha").equals(senha)) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			throw new SistemaException(e);
		}
		return false;
	}

	@Override
	public boolean verificaPessoaAlunoExiste(Aluno a) throws SistemaException {
		// TODO Auto-generated method stub
		return false;
	}

}
