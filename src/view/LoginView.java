package view;

import controller.LoginController;
import controller.persistence.exceptions.SistemaException;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import view.interfaces.Tela;

public class LoginView implements Tela {
	// Campos de Texto
	private TextField txtEmail = new TextField();
	private TextField txtSenha = new TextField();
	
	//controller
	private LoginController control;
	
	@Override
	public Pane render()  {
		
		try {
			control = new LoginController();
		} catch (SistemaException e) {
			alert(AlertType.ERROR, "Ao ao inicializar o sistema");
		}
		
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
		Label lbMatricula =new Label("Email:");

		Label lbSenha = new Label("Senha:");

		paneForm.add(lbMatricula, 0, 0);
		paneForm.add(lbSenha, 0, 1);
	
		//Campos de texto
		paneForm.add(txtEmail, 1, 0);		
		paneForm.add(txtSenha, 1, 1);
		
		vincularPropriedades();
		
		//Botões
		Button btnLoginO = new Button("Logar como Orientador");
		btnLoginO.setStyle("-fx-padding: 15px;");
		paneForm.add(btnLoginO, 0, 2);
		btnLoginO.setOnAction(e -> {
			try {
				if(control.verificaLogin()) {
					control.loginOrientador(new MenuOrientador());
				}
			} catch (SistemaException err) {
				alert(AlertType.ERROR, "Erro ao logar");
				err.printStackTrace();
			}
		});
		//Btn Aluno
		Button btnLoginA = new Button("Logar como Aluno");
		btnLoginA.setStyle("-fx-padding: 15px;");
		paneForm.add(btnLoginA, 1, 2);
		btnLoginA.setOnAction(e -> {
			try {
				if(control.verificaLogin()) {
					control.loginAluno(new MenuAluno());
				}
			} catch (SistemaException err) {
				alert(AlertType.ERROR, "Erro ao logar");
				err.printStackTrace();
			}
		});
		
		paneForm.setStyle("-fx-padding: 10px;-fx-background-color: #f1faee;");
		panePrincipal.setCenter(paneForm);
		
		return panePrincipal;
	}
	
	  public void alert(AlertType tipo, String msg) { 
	        Alert alertWindow = new Alert(tipo);
	        alertWindow.setHeaderText("Alerta");
	        alertWindow.setContentText(msg);
	        alertWindow.showAndWait();
	    }
	  public void vincularPropriedades() { 
	        Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
	        Bindings.bindBidirectional(txtSenha.textProperty(), control.senhaProperty());
	  }
}