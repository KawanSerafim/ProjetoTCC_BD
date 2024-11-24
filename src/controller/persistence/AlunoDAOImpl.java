package controller.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import controller.persistence.exceptions.SistemaException;
import model.dao.AlunoDAO;
import model.dao.PessoaDAO;
import model.entidades.Aluno;
import model.entidades.Orientador;
import model.entidades.Pessoa;
import model.persistence.Conexao;

public class AlunoDAOImpl implements AlunoDAO {

	@Override
	public void inserir(Aluno a) throws SistemaException {
		try {
			//Primeiro insere a entidade mae, Pessoa
			inserirPessoa(a);
			String SQL = """
			          INSERT INTO Aluno (pessoaId, grupoId, ra, turno, semestre, curso)
			          VALUES (?, ?, ?, ?, ?, ?)
			          """;
				 //Busca a instancia da conexao e instancia um java.sql.Connection
				  Connection con = Conexao.getInstancia().getConnection();
			      PreparedStatement stm = con.prepareStatement(SQL);
			      stm.setInt(1, a.getId());
			      stm.setNull(2, Types.VARCHAR);
			      stm.setString(3, a.getRa());
			      stm.setString(4, a.getTurno());
			      stm.setInt(5, a.getSemestre());
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
		//busca o id mais recente e acrescenta +1
			int idNovo = buscaId("Pessoa");
			idNovo+= 1;
			a.setId(idNovo);
			try {
				//Verifica se ja existe pessoa com esse email
				String SQL = """
				          INSERT INTO Pessoa (senha, nome, email)
				          VALUES (?, ?, ?)
				          """;
				      Connection con = Conexao.getInstancia().getConnection();
				      PreparedStatement stm = con.prepareStatement(SQL);
				      stm.setString(1, a.getSenha());
				      stm.setString(2, a.getNome());
				      stm.setString(3, a.getEmail());
				      int i = stm.executeUpdate();
				      System.out.println(i);
				      con.close();
		    } catch (SQLException er) {
		      int id = buscaId("Pessoa");
		      voltaId("Pessoa", id);
		      er.printStackTrace();
		      throw new SistemaException(er);
		    }
		
	}
	
	public int buscaId(String tabela) throws SistemaException{
		//metodo para buscar o ultimo id adicionado e retornar o valor do proximo
		int idAtual = 0;
		try {
			String SQL = """
			        SELECT IDENT_CURRENT( ? ) as id
			          """;
				 //Busca a instancia da conexao e instancia um java.sql.Connection
				  Connection con = Conexao.getInstancia().getConnection();
			      PreparedStatement stm = con.prepareStatement(SQL);
			      stm.setString(1,"'"+tabela +"'");
			      ResultSet rs = stm.executeQuery();
			      while(rs.next()) {
			    	  idAtual = rs.getInt("id");
			      }
			      con.close(); 
		    } catch (SQLException er) {
		      er.printStackTrace();
		      throw new SistemaException(er);
		    }
			return idAtual;
	}
	
	
	private void voltaId(String tabela, int id) throws SistemaException {
		//Caso ocorreu erro na hora da inserção, volta o numero do id para a proxima inserção
		try {
					
			String SQL = """
			          DBCC CHECKIDENT(?, RESEED, ?)
			          """;
			      Connection con = Conexao.getInstancia().getConnection();
			      PreparedStatement stm = con.prepareStatement(SQL);
			      stm.setString(1, " ' "+ tabela + " ' ");
			      stm.setInt(2, id);
			      ResultSet rs = stm.executeQuery();
			      System.out.println(rs.toString());
			      con.close();
	    } catch (SQLException er) {
	      er.printStackTrace();
	      throw new SistemaException(er);
	    }
	}

		

	@Override
	public void atualizar(Aluno a) throws SistemaException {
		try {
			 //Atualiza os dados da entidade mae
			  atualizarPessoa(a);
			  //Atualiza os dados de orientador
		      String SQL = """
		          UPDATE Aluno SET ra=?, turno=?, 
		          semestre=?, curso=?, grupoId=?
		          WHERE pessoaId=?
		          """;
		      Connection con = Conexao.getInstancia().getConnection();
		      PreparedStatement stm = con.prepareStatement(SQL);
		      stm.setString(1, a.getRa());
		      stm.setString(2, a.getTurno());
		      stm.setString(3, String.valueOf(a.getSemestre()) );
		      stm.setString(4, a.getCurso());
		      stm.setInt(5, a.getGrupoId());
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
		          UPDATE Pessoa SET senha=?, nome=?, email=?
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
				DELETE FROM Aluno WHERE pessoaId=?
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
						DELETE FROM Pessoa WHERE id=?
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
				//Garante que o identity não vai quebrar
				int id = buscaId("Pessoa");
				voltaId("Pessoa", id);
	}


	@Override
	public boolean verificaPessoaAlunoExiste(Aluno a) throws SistemaException {
		//Select condiconal e com junção de tabelas
		//Verifica se existe um orientador igual ao objeto recebido como parametro
		List<Aluno> lista = new ArrayList<>();
		try {
			String SQL = """
				SELECT p.id, p.nome, p.senha, p.email,
				a.ra, a.curso, a.semestre, a.turno,
				a.grupoId,  FROM Pessoa p
				INNER JOIN Aluno a
				ON p.id = a.pessoaId
				WHERE a.pessoaId=?
				""";
			Connection con = Conexao.getInstancia().getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, a.getId());
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Aluno aluno = new Aluno();
				a.setId(rs.getInt("id"));
				a.setNome(rs.getString("nome"));
				a.setEmail(rs.getString("email"));
				a.setSenha(rs.getString("senha"));
				a.setRa(rs.getString("ra"));
				a.setCurso(rs.getString("curso"));
				a.setTurno(rs.getString("turno"));
				a.setSemestre(rs.getInt("semestre"));
				a.setGrupoId(rs.getInt("grupoId"));
				lista.add(aluno);
			}
		} catch (SQLException e) {
			throw new SistemaException(e);
		}
		//compara valores
		for(Aluno item : lista ) {
			if(item.equals(a)) {
				return true;
			}
		}
		return false;
	}

}
