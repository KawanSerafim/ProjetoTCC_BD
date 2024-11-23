package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import view.interfaces.Tela;

public class LoginView implements Tela {
	// Campos de Texto
	private TextField txtMatricula = new TextField();
	private TextField txtSenha = new TextField();
	
	//controller
	//A fazer: controller do login com conecção no banco de dados
	@Override
	public Pane render()  {
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
				linhaConstraints, linhaConstraints);
		
		//labels
		Label lbMatricula =new Label("Matricula/RA:");

		Label lbSenha = new Label("Senha:");

		paneForm.add(lbMatricula, 0, 0);
		paneForm.add(lbSenha, 0, 1);
	
		//Campos de texto
		paneForm.add(txtMatricula, 1, 0);		
		paneForm.add(txtSenha, 1, 1);
		
		//Botões
		Button btnLogin = new Button("Logar");
		btnLogin.setStyle("-fx-padding: 15px;");
		paneForm.add(btnLogin, 0, 2);
		
		Button btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setStyle("-fx-padding: 15px;");
		btnCadastrar.setOnAction(e -> {
			// Fazer modal para redirecionar para cadastor de aluno ou professor
		});
		paneForm.add(btnCadastrar, 1, 2);
		
		paneForm.setStyle("-fx-padding: 10px;-fx-background-color: #f1faee;");
		panePrincipal.setCenter(paneForm);
		
		return panePrincipal;
	}
}