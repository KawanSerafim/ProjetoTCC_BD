package controller.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.persistence.exceptions.SistemaException;
import model.dao.GrupoDAO;
import model.entidades.Aluno;
import model.entidades.AreaOrientador;
import model.entidades.Grupo;
import model.persistence.Conexao;

public class GrupoDAOImpl implements GrupoDAO {

	@Override
	public void inserir(Grupo grupo) throws SistemaException {
		if(verificaGrupoExiste(grupo) != false) {
			try {
				String SQL = """
				          INSERT INTO grupo (id,orientadorPessoaId,tema)
				          VALUES (?, ?, ?)
				          """;
					 //Busca a instancia da conexao e instancia um java.sql.Connection
					  Connection con = Conexao.getInstancia().getConnection();
				      PreparedStatement stm = con.prepareStatement(SQL);
				      stm.setInt(1, 0);
				      stm.setInt(2, grupo.getOrientador().getId());
				      stm.setString(3, grupo.getTema());
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

	private boolean verificaGrupoExiste(Grupo grupo) throws SistemaException {
		//Select condiconal e com junção de tabelas
		//Verifica se existe um orientador igual ao objeto recebido como parametro
		List<Grupo> lista = new ArrayList<>();
		try {
			String SQL = """
				SELECT *
				FROM grupo
				WHERE id=?
				""";
			Connection con = Conexao.getInstancia().getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, grupo.getId());
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Grupo g = new Grupo();
				g.setId(rs.getInt("id"));
				g.setTema(rs.getString("tema"));
				lista.add(g);
			}
		} catch (SQLException e) {
			throw new SistemaException(e);
		}
		//compara valores
		for(Grupo item : lista ) {
			if(item.equals(grupo)) {
				return true;
			}
		}
		return false;
	}

	public void adicionaAlunosAoGrupo(List<Aluno> alunos, Grupo grupo) throws SistemaException {
        try { 
        	for(Aluno aluno : alunos) {
	            String SQL = """
	                    UPDATE aluno SET grupoId=? 
	                    WHERE pessoaId=?
	                    """;
	            Connection con = Conexao.getInstancia().getConnection();
	            PreparedStatement stm = con.prepareStatement( SQL );
	            stm.setInt(1, grupo.getId());
	            stm.setInt(2, aluno.getId());
	            int i = stm.executeUpdate();
	            con.close();
        	}
        } catch (SQLException e) { 
            e.printStackTrace();
            throw new SistemaException(e);
        }
	}
	
	@Override
	public void atualizar(Grupo grupo) throws SistemaException {
		//Atualiza tema do grupo
		 try { 
	            String SQL = """
	                    UPDATE grupo SET tema=? 
	                    WHERE id=?
	                    """;
	            Connection con = Conexao.getInstancia().getConnection();
	            PreparedStatement stm = con.prepareStatement( SQL );
	            stm.setString(1, grupo.getTema());
	            stm.setInt(2, grupo.getId());
	            int i = stm.executeUpdate();
	            con.close();
	        } catch (SQLException e) { 
	            e.printStackTrace();
	            throw new SistemaException(e);
	        }

	}

	@Override
	public void remover(Grupo grupo) throws SistemaException {
        try {
            String SQL = """
                    DELETE FROM grupo WHERE id = ?
                    """;
            Connection con = Conexao.getInstancia().getConnection();
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1, grupo.getId());
            int i = stm.executeUpdate();
            con.close();
        } catch (SQLException e) { 
            e.printStackTrace();
            throw new SistemaException(e);
        }

	}

}
