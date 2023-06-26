package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.util.Navigation;
import lk.ijse.gdse.util.Route;

import java.io.IOException;

public class AdminDashBoardController {
    public AnchorPane AdminFormContext;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane AdminDashBoarFormContext;

    public void EmployeeOnAAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.ADD_EMPLOYEE, AdminFormContext);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        new Alert(Alert.AlertType.CONFIRMATION, "Are sure..!!").showAndWait();
        Navigation.navigate(Route.LOGIN, AdminDashBoarFormContext);
    }

    public void AddStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.ADD_STOCK,AdminFormContext);
    }

    public void ReportsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.REPORTS, AdminFormContext);
    }

    public void DashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.ADMIN_DASHBOARD, AdminFormContext);
    }
}
