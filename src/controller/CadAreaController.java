package controller;

import java.util.List;

import controller.persistence.AreaOrientadorDAOImpl;
import controller.persistence.exceptions.SistemaException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dao.AreaOrientadorDAO;
import model.entidades.AreaOrientador;
import model.entidades.Orientador;

public class CadAreaController {
	
	private ObservableList<AreaOrientador> lista = FXCollections.observableArrayList();
	private IntegerProperty id = new SimpleIntegerProperty(); 
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty descricao = new SimpleStringProperty();
	
	private int contadorId = 0;
	
	private Orientador user;
	
	//DB
	private AreaOrientadorDAO areaDAO;
	
	public CadAreaController(Orientador user) throws SistemaException {
		this.user = user;
		areaDAO = new AreaOrientadorDAOImpl();
	}
	
	public void entidadeParaTela(AreaOrientador a) {
		if (a != null ) {
			this.id.set(a.getId());
			this.nome.set(a.getNome());
			this.descricao.set(a.getDescricao());
		}
	}
	
	public void cadastrar() throws SistemaException {
			
			AreaOrientador a = new AreaOrientador();
			contadorId += 1;
			a.setId(contadorId);
			a.setNome(this.nome.get());
			a.setDescricao(this.descricao.get());
			
			areaDAO.inserir(a);
			System.out.println("Nova area cadastrada");
			
		}
 
	
	public void buscarTodasAreas() throws SistemaException {
		lista.clear();
		lista.addAll(areaDAO.pesquisarTodos());
	}
	
	public void vincularOrientadorArea(Orientador user, AreaOrientador area) throws SistemaException{
		areaDAO.vincularAreaOrientador(user, area);
	}
	
	public AreaOrientador buscarAreaPorNome(String nome) throws SistemaException {
		return areaDAO.buscarAreaPorNome(nome);
		
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public StringProperty descricaoProperty() {
		return descricao;
	}
	
	public ObservableList<AreaOrientador> listaProperty() {
		return lista;
	}


	
	
}