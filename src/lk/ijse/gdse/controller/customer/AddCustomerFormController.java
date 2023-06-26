package lk.ijse.gdse.controller.customer;

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
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.ServiceTypes;
import lk.ijse.gdse.service.custom.CustomerService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.tm.CustomerTM;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddCustomerFormController {
    public AnchorPane AddCustomerFormContext;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colTel;
    public TableColumn colDelete;
    public TextField txtTel;
    public Label lblIdreg;
    public Label lbltelreg;

    public CustomerService customerService;
    public Button btnUpdate;
    public TextField txtSearch;


    public void initialize() throws SQLException, ClassNotFoundException {

        this.customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);

        btnUpdate.setDisable(true);
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephone"));

        List<CustomerTM> customerTMList = customerService.findAllCustomers().stream().map(c -> new CustomerTM(c.getId(),c.getName(), c.getAddress(), c.getTelephone())).collect(Collectors.toList());

        tblCustomer.setItems(FXCollections.observableArrayList(customerTMList));

        txtSearch.textProperty().addListener((observableValue, pre, curr) ->{
            if (!Objects.equals(pre, curr)){
                tblCustomer.getItems().clear();
                List<CustomerTM> bookList = customerService.searchCustomerByText(curr).stream().map(customer -> new CustomerTM(customer.getId(), customer.getName(), customer.getAddress(), customer.getTelephone())).collect(Collectors.toList());
                tblCustomer.setItems(FXCollections.observableArrayList(bookList));
            }

        } );

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                btnUpdate.setDisable(false);
            }

        });
    }


    public void SaveOnAction(ActionEvent actionEvent) {
        if (txtId.getText().isEmpty() || !txtId.getText().matches("^[a-z0-9]+$")) {
            new Alert(Alert.AlertType.ERROR,"Id invalid or empty").show();
            txtId.selectAll();
            txtId.requestFocus();
            return;
        }
        else if (txtName.getText().isEmpty() || !txtName.getText().matches("^[A-Za-z0-9 ]+$")){
            new Alert(Alert.AlertType.ERROR,"name Cannot be empty or invalid").show();
            txtName.selectAll();
            txtName.requestFocus();
            return;
        }else if (txtAddress.getText().isEmpty() || !txtAddress.getText().matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR,"Address invalid or null").show();
            txtAddress.selectAll();
            txtAddress.requestFocus();
            return;
        }else if (txtTel.getText().isEmpty() || !txtTel.getText().matches("^(071|077)([0-9]{7})$")){
            new Alert(Alert.AlertType.ERROR,"Telephone number invalid").show();
            txtTel.selectAll();
            txtTel.requestFocus();
            return;
        }

        CustomerDTO customer=new CustomerDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtAddress.getText());

        try {
            if(customerService.saveCustomer(customer)==null) {
                new Alert(Alert.AlertType.ERROR,"Failed to Save the member !").show();
                return;
            }
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added !").show();
            tblCustomer.getItems().add(new CustomerTM(customer.getId(),customer.getName(),customer.getAddress(),customer.getTelephone()));
            txtId.clear();
            txtName.clear();
            txtAddress.clear();
            txtTel.clear();
            txtId.requestFocus();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"MemberDTO Already Exists").show();
            txtId.selectAll();
            txtId.requestFocus();
        }



    }



    public void RemoveOnAction(ActionEvent actionEvent) {

    }

    public void UpdateOnAction(ActionEvent actionEvent) throws IOException {
        //Scene scene = new Scene(window);
        URL resource = this.getClass().getResource("/lk/ijse/gdse/view/UpdateCustomerForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        UpdateCustomerFormController controller = fxmlLoader.getController();
        controller.init(tblCustomer.getSelectionModel().getSelectedItem(),this);
        Stage stage = new Stage();
        stage.setTitle("Update/Delete CustomerDTO details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();


    }
    public static ArrayList<String> loadCustomerIDCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cid FROM customer";
        ResultSet result = DBUtil.executeQuery(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }
}
