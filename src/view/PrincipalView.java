package view;

import java.util.HashMap;
import java.util.Map;

import controller.MenuController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.interfaces.Tela;

public class PrincipalView extends Application {
	private Map<String, Tela> telas = new HashMap<>();
	
	public Map<String, Tela> getTelas() {
		return telas;
	}
	
	 @Override
	    public void start(Stage stage) { 
	        BorderPane panePrincipal = new BorderPane();
	        //Preenche Map de telas
	        telas.put("login", new LoginView());
	        telas.put("cadastroOrientador", new CadOrientadorView());
	        telas.put("cadastroAluno", new CadAlunoView());
	        telas.put("cadastroArea", new CadAreaView());

	        //Barra de menus
	        MenuBar menuBar = new MenuBar();
	        //menus e itens deles
	        Menu menusCadastro = new Menu("Cadastro");
	        MenuItem mnuCadOrientador = new MenuItem("Cadastro Orientador");
	        MenuItem mnuCadAluno = new MenuItem("Cadastro Aluno");
	        menusCadastro.getItems().addAll(mnuCadOrientador,mnuCadAluno);
	        	        
	        Menu menusLogin = new Menu("Login");
	        MenuItem mnuLogin = new MenuItem("Login");
	        menusLogin.getItems().addAll(mnuLogin);

	        menuBar.getMenus().addAll( menusCadastro, menusLogin );

	        panePrincipal.setTop( menuBar );
	        

	        mnuCadOrientador.setOnAction( e -> panePrincipal.setCenter( telas.get("cadastroOrientador").render() ) ); 
	        mnuCadAluno.setOnAction( e -> panePrincipal.setCenter( telas.get("cadastroAluno").render() ) ); 
	        mnuLogin.setOnAction(e -> panePrincipal.setCenter(telas.get("login").render()));
	        
	        Scene scn = new Scene( panePrincipal, 800, 600);
	        stage.setScene(scn);
	        stage.setTitle("Agenda de Contatos");
	        stage.show();
	    }
	 
	
	 
	 public static void main(String[] args) {
		 launch(PrincipalView.class,args);
	 }

}
