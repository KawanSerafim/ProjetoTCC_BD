package model.dao;

import controller.persistence.exceptions.SistemaException;

public interface AreaOrientador {
	void inserir(AreaOrientador area) throws SistemaException;
	void atualizar(AreaOrientador area) throws SistemaException;
	void remover(AreaOrientador area) throws SistemaException;
}
