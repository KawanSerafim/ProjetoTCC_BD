package model.dao;

import controller.persistence.exceptions.SistemaException;
import model.entidades.Aluno;
import model.entidades.Orientador;

public interface PessoaDAO {
			Orientador buscaOrientadorPorEmail(String email) throws SistemaException;
			boolean verificaLoginESenha(String email, String senha) throws SistemaException;
			Aluno verificaAlunoPorEmail(String email) throws SistemaException;
}
