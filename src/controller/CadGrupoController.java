package controller;

import controller.persistence.GrupoDAOImpl;
import controller.persistence.exceptions.SistemaException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.dao.GrupoDAO;
import model.entidades.Aluno;
import model.entidades.Grupo;

public class CadGrupoController {
		private IntegerProperty id = new SimpleIntegerProperty();
		private StringProperty tema = new SimpleStringProperty();
		private StringProperty[] nomes;
		
		private int contadorId = 0;
		
		//bd
		private GrupoDAO grupoDao;
		
		private Aluno user;
		
		public CadGrupoController(Aluno user) {
			this.grupoDao = new GrupoDAOImpl();
			this.user = user;
		}
		
		public void gravar() throws SistemaException {
			Grupo g = new Grupo();
			contadorId+= 1;
			g.setId(contadorId);
			g.setTema(this.tema.get());
			grupoDao.inserir(g);
		}
		
		
		public IntegerProperty getId() {
			return id;
		}
		
		public StringProperty[] getNomes() {
			return nomes;
		}
		
		public StringProperty getTema() {
			return tema;
		}
}