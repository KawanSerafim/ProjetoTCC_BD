package model.dao;

import java.util.List;

import controller.persistence.exceptions.SistemaException;
import model.entidades.Aluno;
import model.entidades.Grupo;

public interface GrupoDAO {
	void inserir(Grupo grupo) throws SistemaException;
	void atualizar(Grupo grupo) throws SistemaException;
	void remover(Grupo grupo) throws SistemaException;
	void adicionaAlunosAoGrupo(List<Aluno> alunos, Grupo grupo) throws SistemaException;
}
