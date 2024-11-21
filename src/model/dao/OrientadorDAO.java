package model.dao;

import controller.persistence.exceptions.OrientadorException;
import model.entidades.Orientador;

public interface OrientadorDAO {

	public void inserir(Orientador orientador) throws OrientadorException;
	public void atualizar(Orientador orientador) throws OrientadorException;
	
}