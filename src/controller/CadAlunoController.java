package controller;

import controller.persistence.AlunoDAOImpl;
import controller.persistence.OrientadorDAOImpl;
import controller.persistence.exceptions.SistemaException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.dao.AlunoDAO;
import model.dao.OrientadorDAO;
import model.entidades.Aluno;

public class CadAlunoController {
	//Pessoa
	private int contadorId = 0;
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty senha = new SimpleStringProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	
	//Aluno
	private StringProperty ra = new SimpleStringProperty();
	private StringProperty curso = new SimpleStringProperty();
	private StringProperty turno = new SimpleStringProperty();
	private IntegerProperty semestre = new SimpleIntegerProperty();
	
	//BD
	private AlunoDAO alunoDAO; 
	
	public CadAlunoController() throws SistemaException {
		alunoDAO = new AlunoDAOImpl();
	}
	
	
	public void cadastrar() throws SistemaException {
		
		Aluno a = new Aluno();
		contadorId += 1;
		a.setId(contadorId);
		a.setNome(this.nome.get());
		a.setEmail(this.email.get());
		a.setSenha(this.senha.get());
		a.setRa(this.ra.get());
		a.setCurso(this.curso.get());
		a.setTurno(this.turno.get());
		a.setSemestre( this.semestre.get());
		alunoDAO.inserir(a);
		System.out.println("Novo aluno cadastrado");
		
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

	public StringProperty getRa() {
		return ra;
	}

	public StringProperty getCurso() {
		return curso;
	}

	public StringProperty getTurno() {
		return turno;
	}

	public IntegerProperty getSemestre() {
		return semestre;
	}
		
}