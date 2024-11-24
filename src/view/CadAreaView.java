package view;

import java.util.List;

import controller.CadAreaController;
import controller.persistence.exceptions.SistemaException;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.entidades.AreaOrientador;
import model.entidades.Orientador;
import view.interfaces.TelaLogadoOrientador;

public class CadAreaView implements TelaLogadoOrientador {
	private Label lbId = new Label("");
	//ComboBox
	private ComboBox<String> cbArea = new ComboBox<>();
	//lista
	private TableView<AreaOrientador> tableView = new TableView<>();
	
	private CadAreaController control;
	
	private Orientador user;
	
	
	@Override
	public Orientador getUser() {
		return this.user;
	}
	
	@Override
	public void setUser(Orientador user) {
		this.user = user;
		
	}
	
	@Override
	public Pane render() {
		try { 
            control = new CadAreaController(this.user);
        } catch (SistemaException e) { 
            alert(AlertType.ERROR, "Ao ao inicializar o sistema");
        }
		
		BorderPane panePrincipal = new BorderPane();
		GridPane paneform = new GridPane();
		
		paneform.add(lbId, 0, 0);
		paneform.add(new Label("Escolha sua area de atuação principal"), 1, 0);
		paneform.add(cbArea,0,1);
		
		preencherAreas();
		
		Button btnAdicionar = new Button("Cadastrar Área");
		btnAdicionar.setOnAction(e -> {
			try {
				control.cadastrar();
			} catch (SistemaException err) {
				alert(AlertType.ERROR, "Erro ao cadastrar área");
			}
		});
		paneform.add(btnAdicionar, 1, 2);
		
		Button btnVincular = new Button("Vincular Área");
		btnVincular.setOnAction(e -> {
			try {
				control.vincularOrientadorArea(this.user, control.buscarAreaPorNome(this.cbArea.getValue()));
			} catch (SistemaException err) {
				alert(AlertType.ERROR, "Erro ao cadastrar área");
			}
		});
		paneform.add(btnVincular, 2, 2);
		
		panePrincipal.setCenter(paneform);
		
		panePrincipal.setBottom(tableView);
		
		return panePrincipal;
	}
	
    private void preencherAreas() {
    	List<AreaOrientador> areas = tableView.getItems();
		for(AreaOrientador area : areas) {
			cbArea.getItems().add(area.getNome());
		}
	}

	private void alert(AlertType tipo, String msg) {
		Alert alertWindow = new Alert(tipo);
		alertWindow.setHeaderText("Alerta");
		alertWindow.setContentText(msg);
		alertWindow.showAndWait();
	}
    public void gerarColunas() { 
        TableColumn<AreaOrientador, Integer> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<AreaOrientador, Integer>("id"));

        TableColumn<AreaOrientador, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<AreaOrientador, String>("nome"));

        TableColumn<AreaOrientador, String> col3 = new TableColumn<>("Descricao");
        col3.setCellValueFactory(new PropertyValueFactory<AreaOrientador, String>("descricao"));
    
        tableView.getColumns().addAll(col1, col2, col3);
        tableView.setItems( control.listaProperty() );

        tableView.getSelectionModel().selectedItemProperty()
        .addListener( (obs, antigo, novo) -> { 
            System.out.println( "Selecionado a área ==> " + novo);
            control.entidadeParaTela( novo );
        });
    }
    
    public void vincularPropriedades() { 
    	 Bindings.bindBidirectional(lbId.textProperty(), control.idProperty(), 
                 (StringConverter) new IntegerStringConverter());
    	cbArea.valueProperty().bindBidirectional(control.nomeProperty());
    }
    
}

