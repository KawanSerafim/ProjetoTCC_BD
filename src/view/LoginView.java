package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class LoginView extends Application {
	// Campos de Texto
	private TextField txtEmail = new TextField();
	private TextField txtSenha = new TextField();
	
	//controller
	
	@Override
	public void start(Stage stage) throws Exception {
		//Paineis
		BorderPane panePrincipal = new BorderPane();
		GridPane paneForm = new GridPane();
		
		//Restrições de coluna e linha
        ColumnConstraints colLabels = new ColumnConstraints();
        colLabels.setPercentWidth(10);
		ColumnConstraints colTextFields = new ColumnConstraints();
		colTextFields.setPercentWidth(90);
		paneForm.getColumnConstraints().addAll(colLabels, colTextFields);
		
		RowConstraints linhaConstraints = new RowConstraints();
		linhaConstraints.setPercentHeight(15.0);
		
		paneForm.getRowConstraints().addAll(linhaConstraints,
				linhaConstraints);
		
		//labels
		paneForm.add(new Label("Email:"), 0, 0);
		paneForm.add(new Label("Senha:"), 0, 1);
	
		//Campos de texto
		paneForm.add(txtEmail, 1, 0);
		paneForm.add(txtSenha, 1, 1);
		
		//Botões
		Button btnLogin = new Button("Logar");
		paneForm.add(btnLogin, 0, 2);
		Button btnCadastrar = new Button("Cadastrar");
		paneForm.add(btnCadastrar, 1, 2);
		
	paneForm.setStyle("-fx-padding: 10px;");
		panePrincipal.setCenter(paneForm);
		
		//Cena e Stage
		Scene sc = new Scene(panePrincipal, 600, 300);
		stage.setTitle("Login");
		stage.setScene(sc);
		stage.show();
	}

	public static void main(String[] args) {
		launch(LoginView.class,args);

	}

}
