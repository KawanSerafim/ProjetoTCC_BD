package view.interfaces;

import java.util.Map;

import model.entidades.Aluno;

public interface TelaLogadoAluno extends Tela {
	void setUser(Aluno user);
	Aluno getUser();
	Map<String, Tela> getTelasAluno();
}
