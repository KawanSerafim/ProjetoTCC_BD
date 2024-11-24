package controller.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.persistence.exceptions.SistemaException;
import model.dao.AreaOrientadorDAO;
import model.entidades.AreaOrientador;
import model.entidades.Orientador;
import model.persistence.Conexao;

public class AreaOrientadorDAOImpl implements AreaOrientadorDAO {

	@Override
	public void inserir(AreaOrientador area) throws SistemaException {
		if(verificaAreaExiste(area) != false) {
			//Primeiro cria a areaOrientador
			try {
				String SQL = """
				          INSERT INTO areaOrientador (id, nome,descricao)
				          VALUES (?, ?, ?)
				          """;
					 //Busca a instancia da conexao e instancia um java.sql.Connection
					  Connection con = Conexao.getInstancia().getConnection();
				      PreparedStatement stm = con.prepareStatement(SQL);
				      stm.setInt(1, 0);
				      stm.setString(2, area.getNome());
				      stm.setString(3, area.getDescricao());
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
		

	@Override
	public void atualizar(AreaOrientador area) throws SistemaException {
		try {
		      String SQL = """
		          UPDATE areaOrientador SET nome=?, descricao=?
		          WHERE id=?
		          """;
		      Connection con = Conexao.getInstancia().getConnection();
		      PreparedStatement stm = con.prepareStatement(SQL);
		      stm.setString(1, area.getNome());
		      stm.setString(2, area.getDescricao());
		      stm.setInt(3, area.getId());
		      int i = stm.executeUpdate();
		      System.out.println(i);
		      con.close();
		    } catch (SQLException er) {
		      er.printStackTrace();
		      throw new SistemaException(er);
		    }
	}

	@Override
	public boolean verificaAreaExiste(AreaOrientador area) throws SistemaException {
		//Select condiconal e com junção de tabelas
		//Verifica se existe um orientador igual ao objeto recebido como parametro
		List<AreaOrientador> lista = new ArrayList<>();
		try {
			String SQL = """
				SELECT *
				FROM areaOrientador
				WHERE id=?
				""";
			Connection con = Conexao.getInstancia().getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, area.getId());
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				AreaOrientador a = new AreaOrientador();
				a.setId(rs.getInt("id"));
				a.setNome(rs.getString("nome"));
				a.setDescricao(rs.getString("descricao"));
				lista.add(a);
			}
		} catch (SQLException e) {
			throw new SistemaException(e);
		}
		//compara valores
		for(AreaOrientador item : lista ) {
			if(item.equals(area)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void vincularAreaOrientador(Orientador orientador, AreaOrientador ar) throws SistemaException {
		try {
		      String SQL = """
		          INSERT INTO associativaOrientadorArea (orientadorPessoaId, areaOrientadorId)
		          VALUES (?,?)
		          """;
		      Connection con = Conexao.getInstancia().getConnection();
		      PreparedStatement stm = con.prepareStatement(SQL);
		      stm.setInt(1, orientador.getId());
		      stm.setInt(2, ar.getId());
		      int i = stm.executeUpdate();
		      System.out.println(i);
		      con.close();
		    } catch (SQLException er) {
		      er.printStackTrace();
		      throw new SistemaException(er);
		    }
		
	}


	@Override
	public List<AreaOrientador> pesquisarTodos() throws SistemaException{
		List<AreaOrientador> lista = new ArrayList<>();
        try { 
            String SQL = """
                    SELECT * FROM areaOrientador       
                    """;
            Connection con = Conexao.getInstancia().getConnection();
            PreparedStatement stm = con.prepareStatement(SQL);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) { 
                AreaOrientador area = new AreaOrientador();
                area.setId(rs.getInt("id"));
                area.setNome(rs.getString("nome"));
                area.setDescricao(rs.getString("descricao"));
                lista.add(area);
            }
            con.close();
        } catch (SQLException e) { 
            e.printStackTrace();
            throw new SistemaException(e);
        }
        return lista;
    }


	@Override
	public AreaOrientador buscarAreaPorNome(String nome) throws SistemaException {
		List<AreaOrientador> lista = new ArrayList<>();
        try { 
            String SQL = """
                    SELECT * FROM areaOrientador
                    WHERE nome LIKE ?     
                    """;
            Connection con = Conexao.getInstancia().getConnection();
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) { 
                AreaOrientador area = new AreaOrientador();
                area.setId(rs.getInt("id"));
                area.setNome(rs.getString("nome"));
                area.setDescricao(rs.getString("descricao"));
                lista.add(area);
            }
            con.close();
        } catch (SQLException e) { 
            e.printStackTrace();
            throw new SistemaException(e);
        }
        return lista.getFirst();
	}

}
