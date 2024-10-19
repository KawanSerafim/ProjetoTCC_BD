package model.entidades;

public class Aluno extends Pessoa {
		
	private String ra;
	private String turno;
	private char semestre;
	
	public Aluno() {}
	
	public Aluno(String ra, String turno, char semestre) {
		
		this.ra = ra;
		this.turno = turno;
		this.semestre = semestre;
		
	}

	public String getRa() {
		return ra;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public char getSemestre() {
		return semestre;
	}

	public void setSemestre(char semestre) {
		this.semestre = semestre;
	}
	
}