package view;

import controller.CadOrientadorController;
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

public class CadOrientadorView implements Tela {
	//Campos de Texto
	private TextField txtNome = new TextField();
	private TextField txtMatricula = new TextField();
	private TextField txtEmail = new TextField();
	private TextField txtSenha = new TextField();
	
	//control
	private CadOrientadorController control;
	
	@Override
	public Pane render() {
		//Paineis
		BorderPane panePrincipal = new BorderPane();
		GridPane paneForm = new GridPane();
		
		//Instancia o contol
		try {
			control = new CadOrientadorController();
		} catch(SistemaException err) {
			alert(AlertType.ERROR, "Ao ao inicializar o sistema");
		}
		
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
		Label lbMatricula =new Label("Matricula:");
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
			try {
				control.cadastrar();
			} catch (SistemaException err) {
				alert(AlertType.ERROR, "Erro ao gravar");
			}
		});
		
		paneForm.add(btnCad, 1, 4);
		
		//Scene e Stage
		panePrincipal.setCenter(paneForm);
		
		return panePrincipal;
		
		
	}
	
    private void alert(AlertType tipo, String msg) {
		Alert alertWindow = new Alert(tipo);
		alertWindow.setHeaderText("Alerta");
		alertWindow.setContentText(msg);
		alertWindow.showAndWait();
	}

	public void vincularPropriedes() { 
        Bindings.bindBidirectional(txtNome.textProperty(), control.getNome());
        Bindings.bindBidirectional(txtMatricula.textProperty(), control.getMatricula());
        Bindings.bindBidirectional(txtEmail.textProperty(), control.getEmail());
        Bindings.bindBidirectional(txtSenha.textProperty(), control.getSenha());
    }
}
