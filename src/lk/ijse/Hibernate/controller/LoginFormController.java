package lk.ijse.Hibernate.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * @author : Hasii-boy
 **/
public class LoginFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtUser;
    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    public void loginOnAction(ActionEvent actionEvent) {

    }

    @FXML
    public void singUpOnAction(ActionEvent actionEvent) {

    }

    @FXML
    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof Button){
            Button button = (Button) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), button);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

        }
        if (event.getSource() instanceof AnchorPane){
            AnchorPane pane = (AnchorPane) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

        }
    }

    @FXML
    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof Button){
            Button button = (Button) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), button);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            button.setEffect(null);
        }
        if (event.getSource() instanceof AnchorPane){
            AnchorPane pane = (AnchorPane) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            pane.setEffect(null);
        }
    }
}
