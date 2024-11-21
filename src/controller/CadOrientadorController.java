package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entidades.Orientador;

public class CadOrientadorController {
	//Pessoa
		private int contadorId = 0;
		private IntegerProperty id = new SimpleIntegerProperty();
		private StringProperty senha = new SimpleStringProperty();
		private StringProperty nome = new SimpleStringProperty();
		private StringProperty email = new SimpleStringProperty();
		
		//Orientador
		private StringProperty matricula = new SimpleStringProperty();
		
		
		public void cadastrar() {
			Orientador o = new Orientador();
			contadorId += 1;
			o.setId(contadorId);
			o.setNome(this.nome.get());
			o.setEmail(this.email.get());
			o.setSenha(this.senha.get());
			o.setMatricula(this.matricula.get());
			System.out.println("Novo orientador cadastrado");	
			mostraOrientador(o);
		}

		private void mostraOrientador(Orientador o) {
				System.out.println("id: " +  o.getId()
											+ "\nnome: " + o.getNome()
											+ "\nemail:"  + o.getEmail()
											+ "\nsenha: " + o.getSenha()
											+ "\nra:"  + o.getMatricula());
							
		}

		public IntegerProperty getId() {
			return id;
		}

		public StringProperty getSenha() {
			return senha;
		}

		public StringProperty getNome() {
			return nome;
		}

		public StringProperty getEmail() {
			return email;
		}

		public StringProperty getMatricula() {
			return matricula;
		}
		
		
	
}