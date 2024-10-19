package model.entidades;

public class Pessoa {

	private int id;
	private String senha;
	private String nome;
	private String email;
	
	public Pessoa() {}
	
	public Pessoa(int id, String senha, String nome, String email) {
		
		this.id = id;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		
	}

	public int getId() {
		return id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}