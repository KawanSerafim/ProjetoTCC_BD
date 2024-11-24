package controller;

import java.util.Map;

import controller.persistence.OrientadorDAOImpl;
import controller.persistence.PessoaDAOImpl;
import controller.persistence.exceptions.SistemaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.dao.OrientadorDAO;
import model.dao.PessoaDAO;
import model.entidades.Aluno;
import model.entidades.Orientador;
import view.MenuAluno;
import view.interfaces.Tela;
import view.interfaces.TelaLogadoAluno;
import view.interfaces.TelaLogadoOrientador;

public class LoginController {
		private StringProperty email = new SimpleStringProperty();
		private StringProperty senha = new SimpleStringProperty();
		
		private PessoaDAO pessoaDAO;
		
		
		public LoginController() throws SistemaException{
			pessoaDAO = new PessoaDAOImpl();
		}
		
		public boolean verificaLogin() throws SistemaException {
			//Verifica se o login e senha estão corretos
			if (pessoaDAO.verificaLoginESenha(email.get(),senha.get())) {
				return true;
			} else {
				return false;
			}
		}

		public void loginOrientador(TelaLogadoOrientador menuOrientador) throws SistemaException {
			//verifica se o orientador esta cadastrado
			Orientador or = pessoaDAO.buscaOrientadorPorEmail(this.email.get());
			if( or != null) {				
				//Mostra o menu do orientador logado
				Map<String, Tela> telas = menuOrientador.getTelasOrientador();
				telas.get("menu").render();
			}
		}
		
		public void loginAluno(TelaLogadoAluno menuAluno) throws SistemaException {
			//verifica se o orientador esta cadastrado
			Aluno a = pessoaDAO.verificaAlunoPorEmail(this.email.get());
			if( a != null) {				
				//Mostra o menu do orientador logado
				Map<String, Tela> telas = menuAluno.getTelasAluno();
				telas.get("menu").render();
			}
			
		}
		public StringProperty emailProperty() {
			return email;
		}
		
		public StringProperty senhaProperty() {
			return senha;
		}

		
		
		
		
}
