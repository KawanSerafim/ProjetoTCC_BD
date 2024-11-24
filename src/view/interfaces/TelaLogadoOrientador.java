package view.interfaces;

import model.entidades.Orientador;

public interface TelaLogadoOrientador extends Tela {
	void setUser(Orientador user);
	Orientador getUser();
}
