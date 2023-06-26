package lk.ijse.gdse.controller.customer;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.controller.customer.AddCustomerFormController;
import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.ServiceTypes;
import lk.ijse.gdse.service.custom.CustomerService;
import lk.ijse.gdse.service.exception.InUseException;
import lk.ijse.gdse.service.exception.NotFoundException;
import lk.ijse.gdse.tm.CustomerTM;

import java.sql.SQLException;
import java.util.Optional;

public class UpdateCustomerFormController {

    public AnchorPane UpdateCustomerFormContext;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTel;
    public CustomerTM customerTM;
    public CustomerService customerService;
    public AddCustomerFormController addCustomerFormController;
    public Label lblId;
    public Button btnDelete;


    public void init(CustomerTM customerTM,AddCustomerFormController addCustomerFormController){
        this.customerTM=customerTM;
        this.addCustomerFormController =addCustomerFormController;
        fillAllFields(customerTM);
        customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);
    }

    private void fillAllFields(CustomerTM customerTM){
        lblId.setText(customerTM.getId());
        txtName.setText(customerTM.getName());
        txtAddress.setText(customerTM.getAddress());
        txtTel.setText(customerTM.getTelephone());
    }


    public void UpdateCustomerOnAction(ActionEvent actionEvent) {

         if (txtName.getText().isEmpty() || !txtName.getText().matches("^[A-Za-z0-9 ]+$")){
            new Alert(Alert.AlertType.ERROR,"Name Cannot be empty or invalid").show();
            txtName.selectAll();
            txtName.requestFocus();
            return;
        }else if (txtAddress.getText().isEmpty() || !txtAddress.getText().matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR,"Address invalid or null").show();
            txtAddress.selectAll();
            txtAddress.requestFocus();
            return;
        }else if (txtTel.getText().isEmpty() || !txtTel.getText().matches("^(071|077)([0-9]{7})$")){
            new Alert(Alert.AlertType.ERROR,"Invalid telephone Number").show();
            txtTel.selectAll();
            txtTel.requestFocus();
            return;
        }



        CustomerDTO updatedCustomer = new CustomerDTO(customerTM.getId(), txtName.getText(),txtAddress.getText(), txtTel.getText());
        try {
            if(customerService.updateCustomer(updatedCustomer)!=null){
                int selectedIndex = addCustomerFormController.tblCustomer.getSelectionModel()
                        .getSelectedIndex();
                addCustomerFormController.tblCustomer.getItems()
                        .add(selectedIndex,new CustomerTM(updatedCustomer.getId(), updatedCustomer.getName(), updatedCustomer.getAddress(), updatedCustomer.getTelephone()));
                addCustomerFormController.tblCustomer.getItems().remove(selectedIndex+1);
                new Alert(Alert.AlertType.INFORMATION,"CustomerDTO has been successfully updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Failed to update the CustomerDTo details,try again!").show();
            }
        }catch (NotFoundException e){
            new Alert(Alert.AlertType.WARNING,"Customer not found for given id!").show();
        }



    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure to delete the customer", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.YES){

            try {
                customerService.deleteCustomer(customerTM.getId());
                new Alert(Alert.AlertType.INFORMATION,"Customer delete successful").show();
                addCustomerFormController.tblCustomer.getItems().
                        removeAll(addCustomerFormController.tblCustomer.getSelectionModel().getSelectedItem());
                btnDelete.getScene().getWindow().hide();

            }catch (InUseException e){
                new Alert(Alert.AlertType.WARNING,"Book already issued some members, please return them before delete!").show();

            }catch (NotFoundException e){
                new Alert(Alert.AlertType.WARNING,"No Customer found for given id!").show();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
}
