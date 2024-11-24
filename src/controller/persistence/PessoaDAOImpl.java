package controller.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.persistence.exceptions.SistemaException;
import model.dao.PessoaDAO;
import model.entidades.Aluno;
import model.entidades.Orientador;
import model.persistence.Conexao;

public class PessoaDAOImpl implements PessoaDAO {

	

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
	public Orientador buscaOrientadorPorEmail(String email) throws SistemaException {
		Orientador orientador = new Orientador();
		try {
			//Select condicIonal e com junção de tabelas para verificar se pessoa é orientador
			String SQL = """
				SELECT p.id, p.nome, p.email, 
				p.senha, o.matricula FROM Pessoa p
				INNER JOIN Orientador o
				ON p.id = o.pessoaID
				WHERE email=?
				""";
			Connection con = Conexao.getInstancia().getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				orientador.setId(rs.getInt("id"));
				orientador.setNome(rs.getString("nome"));
				orientador.setEmail(rs.getString("email"));
				orientador.setSenha(rs.getString("senha"));
				orientador.setMatricula(rs.getString("matricula"));
			}
			con.close();
		} catch (SQLException e) {
			throw new SistemaException(e);
		}
		return orientador;
	}

	@Override
	public Aluno verificaAlunoPorEmail(String email) throws SistemaException {
		Aluno aluno = new Aluno();
		try {
			//Select condicIonal e com junção de tabelas para verificar se pessoa é orientador
			String SQL = """
				SELECT p.id, p.nome, p.email, 
				p.senha, a.grupoId, a.ra, a.turno,
				a.curso, a.semestre FROM Pessoa p
				INNER JOIN Aluno a
				ON p.id = a.pessoaID
				WHERE email=?
				""";
			Connection con = Conexao.getInstancia().getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setEmail(rs.getString("email"));
				aluno.setSenha(rs.getString("senha"));
				aluno.setRa(rs.getString("ra"));
				aluno.setCurso(rs.getString("curso"));
				aluno.setTurno(rs.getString("turno"));
				aluno.setSemestre(rs.getInt("semestre"));
			}
			con.close();
		} catch (SQLException e) {
			throw new SistemaException(e);
		}
		return aluno;
	}

}
