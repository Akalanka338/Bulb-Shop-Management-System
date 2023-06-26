package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.util.Navigation;
import lk.ijse.gdse.util.Route;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginFormContext;
    public AnchorPane LoginContext;
    public TextField Username;
    public PasswordField Password;
    public Label lblMessage;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        checkLogin();

    }

    private void checkLogin() throws IOException {
        if (Username.getText().toString().equals("Admin") && Password.getText().toString().equals("123")) {

            lblMessage.setText("Success");
            Navigation.navigate(Route.DASHBOARD,loginFormContext);
        } else if (Username.getText().toString().equals("cash") && Password.getText().toString().equals("567")) {

            lblMessage.setText("Success");
           Navigation.navigate(Route.CASHIER_DASHBOARD,loginFormContext);


        }else if (Username.getText().isEmpty()&&Password.getText().isEmpty()){

            lblMessage.setText("enter username and password");
        }else {

            lblMessage.setText("wrong username or password");
        }


    }
}
