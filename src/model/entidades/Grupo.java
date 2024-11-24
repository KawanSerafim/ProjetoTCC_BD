package model.entidades;

public class Grupo {

	private int id;
	private String tema;
	private Orientador orientador;
	
	public Grupo() {}
	
	public Grupo(int id, String tema) {
		
		this.id = id;
		this.tema = tema;
		
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id ) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}
	
}