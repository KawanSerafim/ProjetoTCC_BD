package model.entidades;

public class Aluno extends Pessoa {
	
	private String ra;
	private int grupoId;
	private String curso;
	private String turno;
	private int semestre;
	
	public Aluno() {}
	
	//Construtor com as informações da classe pessoa + classe aluno
	public Aluno(int id, String nome, String email, String senha, 
			               String ra, String curso, String turno, int semestre) {
			
		//envia informações para construtor da classe pessoa
		super(id,nome,email,senha);
			
		this.ra = ra;
		this.curso = curso;
		this.turno = turno;
		this.semestre = semestre;
			
	}

	public String getRa() {
		return ra;
	}
	
	public void setRa(String ra) {
		this.ra = ra;
	}
	
	public String getCurso() {
		return curso;
	}
	
	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	public int getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(int grupoId) {
		this.grupoId = grupoId;
	}

	
}