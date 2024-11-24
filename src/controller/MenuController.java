package controller;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import view.interfaces.Tela;

public class MenuController {
	private MapProperty<String, Tela> telas = new SimpleMapProperty<>();
	
	public MapProperty<String, Tela> telasProperty() {
		return telas;
	}
}
