package view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.entidades.Orientador;
import view.interfaces.Tela;
import view.interfaces.TelaLogadoOrientador;

public class MenuOrientador implements TelaLogadoOrientador {
	private Map<String, Tela> telasOrientador = new HashMap<>();
	
	private Stage stage;
	
	private Orientador user;
	
	@Override
	public void setUser(Orientador user) {
		this.user = user;
		
	}
	@Override
	public Orientador getUser() {
		return this.user;
	}
	
	public Map<String, Tela> getTelasOrientador() {
		return telasOrientador;
	}	
	
	
	@Override
	public Pane render() {
		BorderPane panePrincipal = new BorderPane();
		
	    //Barra de menus
        MenuBar menuBar = new MenuBar();
        //menus e itens deles
        //Cadastro e atualização
        Menu menuCadastro = new Menu("Cadastro");
        MenuItem cadArea = new MenuItem("Cadastro de área");
        //Voltar
        Menu menuVoltar = new Menu("Voltar");
        MenuItem deslogar = new MenuItem("Deslogar");
        MenuItem menuOrientador = new MenuItem("Menu");
        
        menuCadastro.getItems().add(cadArea);
        menuBar.getMenus().add(menuCadastro);
        cadArea.setOnAction( e -> panePrincipal.setCenter( telasOrientador.get("cadastroArea").render() ) );
        
        menuVoltar.getItems().addAll(deslogar,menuOrientador);
        menuBar.getMenus().add(menuVoltar);
        deslogar.setOnAction( e -> panePrincipal.setCenter( telasOrientador.get("login").render() ) );
        menuOrientador.setOnAction( e -> panePrincipal.setCenter( telasOrientador.get("menu").render() ) );
        
		return panePrincipal;
		
	}
	



}
