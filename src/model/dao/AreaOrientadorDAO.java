package model.dao;

import java.util.List;

import controller.persistence.exceptions.SistemaException;
import model.entidades.AreaOrientador;
import model.entidades.Orientador;

public interface AreaOrientadorDAO {
	void inserir(AreaOrientador area) throws SistemaException;
	void atualizar(AreaOrientador area) throws SistemaException;
	boolean verificaAreaExiste(AreaOrientador area) throws SistemaException;
	void vincularAreaOrientador(Orientador orientador, AreaOrientador ar ) throws SistemaException;
	List<AreaOrientador> pesquisarTodos() throws SistemaException;
	AreaOrientador buscarAreaPorNome(String nome) throws SistemaException;
}
