package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.service.DepatmentService;

public class DepatmentListController implements Initializable {

	private DepatmentService service;

	@FXML
	private TableView<Department> tableViewDepartment;

	@FXML
	private TableColumn<Department, Integer> tableColumnId;

	@FXML
	private TableColumn<Department, String> tableColumnName;

	@FXML
	private Button btNew;

	public void setDepartmentService(DepatmentService service) {
		this.service = service;
	}

	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}

	private ObservableList<Department> obsList;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));

		Stage stage = (Stage) Main.getMainScene().getWindow();

		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());

	}
	
   public void updateTableView() {
	   if(service == null) {
		   throw new IllegalStateException("error sevice");
	   }
	   List<Department> list = service.findAll();
	   obsList = FXCollections.observableArrayList(list);
	   tableViewDepartment.setItems(obsList);
   }
}
