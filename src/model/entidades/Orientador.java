package model.entidades;

public class Orientador extends Pessoa {

	private String matricula;
	
	public Orientador() {}
	
	public Orientador(int id, String nome, String email, String senha, String matricula) {
		
		super(id, nome, email, senha);
		this.matricula = matricula;
		
	}
	
	public Orientador(String matricula) {
		
		this.matricula = matricula;
		
	}

	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}