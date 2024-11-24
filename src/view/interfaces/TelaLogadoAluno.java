package view.interfaces;

import model.entidades.Aluno;

public interface TelaLogadoAluno extends Tela {
	void setUser(Aluno user);
	Aluno getUser();
}
