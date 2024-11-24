package controller;

import java.util.Map;

import controller.persistence.OrientadorDAOImpl;
import controller.persistence.PessoaDAOImpl;
import controller.persistence.exceptions.SistemaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.dao.OrientadorDAO;
import model.dao.PessoaDAO;
import model.entidades.Orientador;
import view.interfaces.Tela;

public class LoginController {
		private StringProperty email = new SimpleStringProperty();
		private StringProperty senha = new SimpleStringProperty();
		
		private PessoaDAO pessoaDAO;
		private OrientadorDAO orientadorDAO;
		
		private MenuController menu;
		
		public LoginController() {
			pessoaDAO = new PessoaDAOImpl();
			orientadorDAO = new OrientadorDAOImpl();
		}
		
		public void verificaLogin() throws SistemaException {
			if (pessoaDAO.verificaLoginESenha(email.get(),senha.get())) {
				Orientador or = orientadorDAO.buscarOrientadorPorEmail(email.get());
				loginOrientador(or);
			}
		}

		private void loginOrientador(Orientador or) {
			menu = new MenuController();
			Map<String, Tela>telas = (Map<String, Tela>) menu.telasProperty();
			telas.get("cadastroArea").render();
		}
		
		
		
}
