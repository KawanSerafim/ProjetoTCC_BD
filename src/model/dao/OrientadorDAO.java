package model.dao;

import controller.persistence.exceptions.SistemaException;
import model.entidades.Orientador;

public interface OrientadorDAO {

	public void inserir(Orientador orientador) throws SistemaException;
	public void atualizar(Orientador orientador) throws SistemaException;
    public Orientador buscarOrientadorPorEmail(String email) throws SistemaException;
	public boolean verificaPessoaOrientadorExiste(Orientador orientador) throws SistemaException;
	
}