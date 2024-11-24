package view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.entidades.Aluno;
import model.entidades.Orientador;
import view.interfaces.Tela;
import view.interfaces.TelaLogadoAluno;

public class MenuAluno implements TelaLogadoAluno {
	private Map<String, Tela> telasAluno = new HashMap<>();
	
	private Aluno user;
	
	@Override
	public void setUser(Aluno user) {
		this.user = user;
		
	}
	@Override
	public Aluno getUser() {
		return this.user;
	}
	
	public Map<String, Tela> getTelasAluno() {
		return telasAluno;
	}	
	
	
	@Override
	public Pane render() {
		BorderPane panePrincipal = new BorderPane();
		 //Preenche Map de telas
		telasAluno.put("login", new LoginView());
		//telasOrientador.put("cadastroGrupo", new CadGrupoView());
		telasAluno.put("menu", new MenuAluno());
	    //Barra de menus
        MenuBar menuBar = new MenuBar();
        //menus e itens deles
        //Cadastro e atualização
        Menu menuCadastro = new Menu("Cadastro");
        MenuItem cadGrupo = new MenuItem("Cadastro de grupo");
        //Voltar
        Menu menuVoltar = new Menu("Voltar");
        MenuItem deslogar = new MenuItem("Deslogar");
        MenuItem menuAluno = new MenuItem("Menu");
        
        menuCadastro.getItems().add(cadGrupo);
        menuBar.getMenus().add(menuCadastro);
        //cadGrupo.setOnAction( e -> panePrincipal.setCenter( telasOrientador.get("cadastroGrupo").render() ) );
        
        menuVoltar.getItems().addAll(deslogar,menuAluno);
        menuBar.getMenus().add(menuVoltar);
        deslogar.setOnAction( e -> panePrincipal.setCenter( telasAluno.get("login").render() ) );
        menuAluno.setOnAction( e -> panePrincipal.setCenter( telasAluno.get("menu").render() ) );
        
		return panePrincipal;
		
	}
	

}
