package model.entidades;

public class Pessoa {

	private int id;
	private String nome;
	private String email;
	private String senha;
	
	public Pessoa() {}
	
	public Pessoa(int id,  String nome, String email, String senha) {
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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