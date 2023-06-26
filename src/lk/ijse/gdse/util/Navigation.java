package lk.ijse.gdse.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Navigation {
    private static AnchorPane pane;
    public static void navigate(Route route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case LOGIN:
                window.setTitle("Login Form");
                initUI("LoginForm.fxml");
                break;
            case DASHBOARD:
                window.setTitle("Admin DashBoard Form");
                initUI("AdminDashBoard.fxml");
                break;
            case CASHIER_DASHBOARD:
                window.setTitle("Cashier DashBoard Form");
                initUI("CashierDashboard.fxml");
                break;



            case CUSTOMER:
                window.setTitle("Cashier DashBoard Form");
                initUI("CustomerManageForm.fxml");
                break;

            case ORDER_MANAGE:
                window.setTitle("Cashier DashBoard Form");
                initUI("OrderManageForm.fxml");
                break;

            case ADD_CUSTOMER:
                window.setTitle("Cashier DashBoard Form");
                initUI("AddCustomerForm.fxml");
                break;

            case ADD_STOCK:
                window.setTitle("Admin DashBoard Form");
                initUI("ManageStock.fxml");
                break;

            case ADMIN_DASHBOARD:
                window.setTitle("Admin DashBoard Form");
                initUI("DashBoard.fxml");
                break;

            case ADD_ITEM:
                window.setTitle("Admin DashBoard Form");
                initUI("AddItemForm.fxml");
                break;

            case ADD_EMPLOYEE:
                window.setTitle("Admin DashBoard Form");
                initUI("AddEmployeeForm.fxml");
                break;

            case REPORTS:
                window.setTitle("Admin DashBoard Form");
                initUI("reports.fxml");
                break;

            case UPDATE:
                window.setTitle("Admin DashBoard Form");
                initUI("updateCustomerForm.fxml");
                break;






            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/gdse/view/" + location)));
    }


}
