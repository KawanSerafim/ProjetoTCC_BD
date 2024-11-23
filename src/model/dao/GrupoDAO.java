package model.dao;

import controller.persistence.exceptions.SistemaException;
import model.entidades.Grupo;

public interface GrupoDAO {
	void inserir(Grupo grupo) throws SistemaException;
	void atualizar(Grupo grupo) throws SistemaException;
	void remover(Grupo grupo) throws SistemaException;
}
