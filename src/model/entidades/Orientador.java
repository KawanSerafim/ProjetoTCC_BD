package model.entidades;

public class Orientador extends Pessoa {

	private String matricula;
	
	public Orientador() {}
	
	public Orientador(String matricula) {
		
		this.matricula = matricula;
		
	}

	public String getMatricula() {
		return matricula;
	}
	
}