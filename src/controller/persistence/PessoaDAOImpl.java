package controller.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.persistence.exceptions.SistemaException;
import model.dao.PessoaDAO;
import model.persistence.Conexao;

public class PessoaDAOImpl implements PessoaDAO {

	@Override
	public Integer buscaIdPessoaPorEmail(String email) throws SistemaException {
		Integer id = null;
		try {
			//Select condicIonal
			String SQL = """
				SELECT id FROM pessoa p
				WHERE p.email = ?
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

}
