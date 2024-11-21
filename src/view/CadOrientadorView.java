package view;

import controller.CadOrientadorController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class CadOrientadorView extends Application {
	//Campos de Texto
	private TextField txtNome = new TextField();
	private TextField txtMatricula = new TextField();
	private TextField txtEmail = new TextField();
	private TextField txtSenha = new TextField();
	
	//control
	private CadOrientadorController control = new CadOrientadorController();
	
	@Override
	public void start(Stage stage) throws Exception {
		//Paineis
		BorderPane panePrincipal = new BorderPane();
		GridPane paneForm = new GridPane();
		
		//Restrições de coluna e linha
        ColumnConstraints colLabels = new ColumnConstraints();
        colLabels.setPercentWidth(30);
		ColumnConstraints colTextFields = new ColumnConstraints();
		colTextFields.setPercentWidth(70);
		paneForm.getColumnConstraints().addAll(colLabels, colTextFields);
		
		RowConstraints linhaConstraints = new RowConstraints();
		linhaConstraints.setPercentHeight(7.0);
		
		paneForm.getRowConstraints().addAll(linhaConstraints,
				linhaConstraints, linhaConstraints, linhaConstraints, 
				linhaConstraints, linhaConstraints, linhaConstraints);
		
		//Labels
		Label lbNome =new Label("Nome:");
		paneForm.add(lbNome, 0, 0);
		Label lbMatricula =new Label("RA:");
		paneForm.add(lbMatricula, 0, 1);
		Label lbEmail =new Label("Email");
		paneForm.add(lbEmail, 0, 2);
		Label lbSenha = new Label("Senha:");
		paneForm.add(lbSenha, 0, 3);
		
	    //TextFields
		paneForm.add(txtNome, 1, 0);
		paneForm.add(txtMatricula, 1, 1);
		paneForm.add(txtEmail, 1, 2);
		paneForm.add(txtSenha, 1, 3);
	
		
		vincularPropriedes();
		//Botao(Cadastrar)
		Button btnCad = new Button("Cadastrar");
		btnCad.setStyle("-fx-padding: 14px;");
		
		btnCad.setOnAction(e -> {
			control.cadastrar();
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cadastro de Aluno");
            alert.setHeaderText("Informativo");
            alert.setContentText("Orientador foi cadastrado com sucesso");
            alert.show();
		});
		
		paneForm.add(btnCad, 1, 4);
		
		//Scene e Stage
		panePrincipal.setCenter(paneForm);
		Scene sc = new Scene(panePrincipal, 800,600);
		stage.setTitle("Cadastro Orientador");
		stage.setScene(sc);
		stage.show();
	}
	
    public void vincularPropriedes() { 
        Bindings.bindBidirectional(txtNome.textProperty(), control.getNome());
        Bindings.bindBidirectional(txtMatricula.textProperty(), control.getMatricula());
        Bindings.bindBidirectional(txtEmail.textProperty(), control.getEmail());
        Bindings.bindBidirectional(txtSenha.textProperty(), control.getSenha());
    }
	public static void main(String[] args) {
		launch(CadOrientadorView.class,args);
	}

}
