package view;

import java.util.ArrayList;
import java.util.List;

import controller.CadAlunoController;
import controller.persistence.exceptions.SistemaException;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.entidades.Aluno;
import view.interfaces.Tela;
import view.interfaces.TelaLogadoAluno;

public class CadAlunoView implements TelaLogadoAluno{
	
	//Campos de Texto
	private TextField txtNome = new TextField();
	private TextField txtRa = new TextField();
	private TextField txtEmail = new TextField();
	private TextField txtSenha = new TextField();
	
		
	//Lista de cursos
	private List<String> cursos = new ArrayList<>();
	
	//ComboBox
	ComboBox<String> cbCurso = new ComboBox<>();

	ComboBox<String> cbTurno = new ComboBox<>();

	ComboBox<Integer> cbSemestre = new ComboBox<>();
	
	private Aluno user;

	
	@Override
	public void setUser(Aluno user) {
		this.user = user;
		
	}

	@Override
	public Aluno getUser() {
		return this.user;
	}
	
	
	
	//control
	private CadAlunoController control;
	
	@Override
	public Pane render() {
		//Paineis
		BorderPane panePrincipal = new BorderPane();
		GridPane paneForm = new GridPane();
		
		//Instancia o control
		try {
			control = new CadAlunoController();
		} catch (SistemaException e) {
			alert(AlertType.ERROR, "Erro ao inicializar");
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
		Label lbMatricula =new Label("RA:");
		paneForm.add(lbMatricula, 0, 1);
		Label lbEmail =new Label("Email");
		paneForm.add(lbEmail, 0, 2);
		Label lbSenha = new Label("Senha:");
		paneForm.add(lbSenha, 0, 3);
		Label lbCurso= new Label("Selecione seu Curso:");
		paneForm.add(lbCurso, 0, 4);
		Label lbTurno= new Label("Selecione seu Turno:");
		paneForm.add(lbTurno, 0, 5);
		Label lbSemestre = new Label("Selecione seu Semestre:");
		paneForm.add(lbSemestre, 0, 6);
		
	    //TextFields
		paneForm.add(txtNome, 1, 0);
		paneForm.add(txtRa, 1, 1);
		paneForm.add(txtEmail, 1, 2);
		paneForm.add(txtSenha, 1, 3);
		
		// Botões(Curso, Turno, Semestre)
		preencheLista();
		cbCurso.getItems().addAll(cursos);
		paneForm.add(cbCurso, 1, 4);
		

		
		cbTurno.getItems().addAll("Manhã", "Tarde", "Noite", "EAD");
		paneForm.add(cbTurno, 1, 5);
		
		cbSemestre.getItems().addAll(5,6);
		paneForm.add(cbSemestre, 1, 6);
		
		//Botao(Cadastrar)
		Button btnCad = new Button("Cadastrar");
		btnCad.setStyle("-fx-padding: 14px;");
		
		vincularPropriedes();
		vincularComboBox();
		
		btnCad.setOnAction(e -> {
			try {
				control.cadastrar();
			} catch (SistemaException e1) {
				alert(AlertType.ERROR, "Erro ao gravar dados");
			}
			alert(AlertType.INFORMATION,"Aluno foi cadastrado com sucesso");
		});
		
		paneForm.add(btnCad, 1, 7);
		
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

	private void preencheLista() {
			this.cursos.add("Análise e desenvolvimento de sistemas");
			this.cursos.add("Desenvolvimento de Sistemas Multiplataforma");
			this.cursos.add("Logistica");
			this.cursos.add("Recursos Humanos");
	}
	
    public void vincularPropriedes() { 
        Bindings.bindBidirectional(txtNome.textProperty(), control.getNome());
        Bindings.bindBidirectional(txtRa.textProperty(), control.getRa());
        Bindings.bindBidirectional(txtEmail.textProperty(), control.getEmail());
        Bindings.bindBidirectional(txtSenha.textProperty(), control.getSenha());
    }
    
    public void vincularComboBox() {
    	cbCurso.valueProperty().bindBidirectional(
    		    control.getCurso());
    	 cbTurno.valueProperty().bindBidirectional(
    			 control.getTurno());
    	 cbSemestre.valueProperty().bindBidirectional(
    			 control.getSemestre().asObject());
    	

    }



}