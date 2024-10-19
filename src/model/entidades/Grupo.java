package model.entidades;

import java.util.List;

public class Grupo {

	private int id;
	private String tema;
	private List<Aluno> alunos;
	private Orientador orientador;
	
	public Grupo() {}
	
	public Grupo(int id, String tema, List<Aluno> alunos) {
		
		this.id = id;
		this.tema = tema;
		this.alunos = alunos;
		
	}

	public int getId() {
		return id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}
	
}