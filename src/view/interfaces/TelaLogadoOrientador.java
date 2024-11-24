package view.interfaces;

import java.util.Map;

import model.entidades.Orientador;

public interface TelaLogadoOrientador extends Tela {
	void setUser(Orientador user);
	Orientador getUser();
	public Map<String, Tela> getTelasOrientador();
}
