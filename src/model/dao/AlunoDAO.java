package model.dao;

import controller.persistence.exceptions.SistemaException;
import model.entidades.Aluno;
import model.entidades.Orientador;

public interface AlunoDAO {
	void inserir(Aluno a)throws SistemaException;
	void atualizar(Aluno a)throws SistemaException;
	void remover(Aluno a)throws SistemaException;
	boolean verificaPessoaAlunoExiste(Aluno a) throws SistemaException;
}
