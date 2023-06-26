package lk.ijse.gdse.controller.customer;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.util.Navigation;
import lk.ijse.gdse.util.Route;

import java.io.IOException;

public class CustomerManageFormController {

    public AnchorPane ManageCustomerFormContext;

    public void AddCustomeronAction(ActionEvent actionEvent) throws IOException {
       Navigation.navigate(Route.ADD_CUSTOMER, ManageCustomerFormContext);

    }
}
