package lk.ijse.gdse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.gdse.util.Navigation;
import lk.ijse.gdse.util.Route;

import java.io.IOException;
import java.net.URL;

public class WelcomeFormController {

    public Rectangle rctContainer;
    public Rectangle rctLoading;
    public Label lblLoading;
    public ImageView welcomeFormContext;
    public AnchorPane wWelcomeForm;
    public ImageView WelcomeForm;


    public void initialize() {
        Timeline timeline = new Timeline();

        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(500), actionEvent -> {
            lblLoading.setText("Initializing Application....");
            rctLoading.setWidth(rctContainer.getWidth() * 0.3);
        });

        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1000), actionEvent -> {
            lblLoading.setText("Loading Internal Resources....");
            rctLoading.setWidth(rctContainer.getWidth() * 0.5);
        });
        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(1500), actionEvent -> {
            lblLoading.setText("Loading Images....");
            rctLoading.setWidth(rctContainer.getWidth() * 0.6);
        });
        KeyFrame keyFrame4 = new KeyFrame(Duration.millis(2000), actionEvent -> {
            lblLoading.setText("Loading UIs....");
            rctLoading.setWidth(rctContainer.getWidth() * 0.8);
        });
        KeyFrame keyFrame5 = new KeyFrame(Duration.millis(2500), actionEvent -> {
            lblLoading.setText("Welcome to Bulb Management System v1.0.0");
            rctLoading.setWidth(rctContainer.getWidth());
        });
        KeyFrame keyFrame6 = new KeyFrame(Duration.millis(3000), actionEvent -> {
            /*try {
                URL resource = this.getClass().getResource("/view/LoginForm.fxml");
                Stage stage = new Stage();
                AnchorPane container = FXMLLoader.load(resource);
                AnchorPane pneContainer = (AnchorPane)container.lookup("#wWelcomeForm");
                Navigation.init(pneContainer);
                stage.setScene(new Scene(container));
                stage.centerOnScreen();
                Stage window =(Stage) lblLoading.getScene().getWindow();
                window.hide();
                Navigation.navigate(Route.LOGIN,wWelcomeForm);
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
            try {

                Navigation.navigate(Route.LOGIN,wWelcomeForm);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5,keyFrame6);
        timeline.playFromStart();
    }
    }
