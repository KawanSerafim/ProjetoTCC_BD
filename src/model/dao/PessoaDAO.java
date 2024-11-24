package model.dao;

import controller.persistence.exceptions.SistemaException;
import model.entidades.Pessoa;

public interface PessoaDAO {
			Integer buscaIdPessoaPorEmail(String email) throws SistemaException;
			boolean verificaLoginESenha(String email, String senha) throws SistemaException;
			
}
