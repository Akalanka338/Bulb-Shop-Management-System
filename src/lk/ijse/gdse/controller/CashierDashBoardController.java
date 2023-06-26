package lk.ijse.gdse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.gdse.util.Navigation;
import lk.ijse.gdse.util.Route;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CashierDashBoardController {
    public AnchorPane DashBoarFormContext;
    public Label lblTime;
    public AnchorPane DetailsFormContext;


    public void initialize(){
        setDateAndTome();

    }

    public void setDateAndTome(){
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd                            HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));

        }),new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

    }
    public void AddOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.ORDER_MANAGE,DetailsFormContext);
    }

    public void CustomerManageOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.CUSTOMER,DetailsFormContext);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        new Alert(Alert.AlertType.CONFIRMATION, "Are sure..!!").showAndWait();
        Navigation.navigate(Route.LOGIN, DashBoarFormContext);
    }
}
