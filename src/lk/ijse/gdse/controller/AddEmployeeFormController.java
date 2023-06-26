package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse.controller.customer.UpdateCustomerFormController;
import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.dto.EmployeeDTO;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.ServiceTypes;
import lk.ijse.gdse.service.custom.CustomerService;
import lk.ijse.gdse.service.custom.EmployeeService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.tm.CustomerTM;
import lk.ijse.gdse.tm.EmployeeTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddEmployeeFormController {

    public AnchorPane ManageItemFomContext;
    public TableView <EmployeeTM>tblEmployee;
    public TableColumn coID;
    public TableColumn colFname;
    public TableColumn colLname;
    public TableColumn colTel;
    public TableColumn colDob;
    public TableColumn colRole;
    public TextField txtId;
    public TextField txtRole;
    public TextField txtTel;
    public TextField txtFName;
    public TextField txtLName;
    public TextField txtDob;
    public Label lblNamereg;
    public Label lblLNamereg;
    public Label lblRolereg;
    public Label lblTelReg;


    public EmployeeService employeeService;
    public Button btnUpdate;
    public TextField txtSearch;

    public void initialize() throws SQLException, ClassNotFoundException {

        this.employeeService= ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYEE);

        btnUpdate.setDisable(true);
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("eid"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fname"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lname"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("role"));
        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Telephone"));
        tblEmployee.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dob"));

        List<EmployeeTM> employeeTMList = employeeService.findAllEmployee().stream().map(e -> new EmployeeTM(e.getEid(),e.getFirst_name(), e.getLast_name(), e.getRole(),e.getTelephone(),e.getDob())).collect(Collectors.toList());

        tblEmployee.setItems(FXCollections.observableArrayList(employeeTMList));

        txtSearch.textProperty().addListener((observableValue, pre, curr) ->{
            if (!Objects.equals(pre, curr)){
                tblEmployee.getItems().clear();
                List<EmployeeTM> employeeList = employeeService.searchEmployeeByText(curr).stream().map(employee -> new EmployeeTM(employee.getEid(),employee.getFirst_name(),employee.getLast_name(),employee.getRole(),employee.getTelephone(),employee.getDob())).collect(Collectors.toList());
                tblEmployee.setItems(FXCollections.observableArrayList(employeeList));
            }

        } );

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                btnUpdate.setDisable(false);
            }

        });
    }

    public void AddOnAction(ActionEvent actionEvent) {
        if (txtId.getText().isEmpty() || !txtId.getText().matches("^[a-z0-9]+$")) {
            new Alert(Alert.AlertType.ERROR,"Id invalid or empty").show();
            txtId.selectAll();
            txtId.requestFocus();
            return;
        }
        else if (txtFName.getText().isEmpty() || !txtFName.getText().matches("^[A-Za-z0-9 ]+$")){
            new Alert(Alert.AlertType.ERROR,"name Cannot be empty or invalid").show();
            txtFName.selectAll();
            txtFName.requestFocus();
            return;

        }else if (txtTel.getText().isEmpty() || !txtTel.getText().matches("^(071|077)([0-9]{7})$")){
            new Alert(Alert.AlertType.ERROR,"Telephone number invalid").show();
            txtTel.selectAll();
            txtTel.requestFocus();
            return;
        }
        EmployeeDTO employee = new EmployeeDTO(txtId.getText(),txtFName.getText(),txtLName.getText(),txtDob.getText(),txtRole.getText(),txtTel.getText());

        try {
            if(employeeService.saveEmployee(employee)==null) {
                new Alert(Alert.AlertType.ERROR,"Failed to Save the employee !").show();
                return;
            }
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added !").show();
            tblEmployee.getItems().add(new EmployeeTM(employee.getEid(),employee.getFirst_name(),employee.getLast_name(),employee.getRole(),employee.getTelephone(),employee.getDob()));

            txtId.clear();
            txtFName.clear();
            txtLName.clear();
            txtTel.clear();
            txtDob.clear();
            txtRole.clear();
            txtTel.clear();
            txtId.requestFocus();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"EmployeeDTO Already Exists").show();
            txtId.selectAll();
            txtId.requestFocus();
        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/gdse/view/UpdateEmployeForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        UpdateEmployeFormController controller = fxmlLoader.getController();
        controller.init(tblEmployee.getSelectionModel().getSelectedItem(),this);
        Stage stage = new Stage();
        stage.setTitle("Update/Delete CustomerDTO details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();

    }
}

