package lk.ijse.Hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

/**
 * @author : Hasii-boy
 **/
public class DashboardFormController {
    @FXML
    public AnchorPane root;
    @FXML
    public ComboBox<String> cmdStudent;
    @FXML
    public Label lblStudent;
    @FXML
    public JFXTextField txtRes_id;
    @FXML
    public ComboBox<String> cmdRoom_type;
    @FXML
    public Label lblReservation;
    @FXML
    public JFXTextField txtStatus;
    @FXML
    public Label lblDate_Time;
    @FXML
    public JFXButton btnReservation;
    @FXML
    public Label lbl01;
    @FXML
    public Label lbl02;
    @FXML
    public JFXButton btnRoom;
    @FXML
    public JFXButton btnStudent;
    @FXML
    public JFXButton btnReserve;


    @FXML
    public void addNewStudentOnAction(ActionEvent actionEvent) {

    }

    @FXML
    public void saveReservationOnAction(ActionEvent actionEvent) {

    }

    @FXML
    public void cancelReservationOnAction(ActionEvent actionEvent) {

    }

    @FXML
    public void roomOnAction(ActionEvent actionEvent) throws IOException {
        getURL(this.getClass().getResource("/lk/ijse/Hibernate/view/room-manage-form.fxml"));
    }

    @FXML
    public void studentOnAction(ActionEvent actionEvent) throws IOException {
        getURL(this.getClass().getResource("/lk/ijse/Hibernate/view/student-manage-form.fxml"));
    }

    @FXML
    public void reserveDetailsOnAction(ActionEvent actionEvent) throws IOException {
        getURL(this.getClass().getResource("/lk/ijse/Hibernate/view/reserve-details-form.fxml"));
    }

    private void getURL(URL resource) throws IOException {
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(new Scene(FXMLLoader.load(resource)));
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    @FXML
    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();

            switch (button.getId()){
                case "btnRoom":
                    lbl01.setText("Manage Room Details");
                    break;
                case "btnStudent":
                    lbl01.setText("Student Details");
                    break;
                case "btnAddNew":
                    lbl01.setText("Add New Student");
                    break;
                case "btnReserve":
                    lbl01.setText("Reserve Details & Key money");
                    break;
                case "btnReservation":
                    lbl02.setText("Save Reservation");
                    break;
                case "btnCancel":
                    lbl02.setText("Cancel Reservation");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), button);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();
        
            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            button.setEffect(glow);
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

            lbl01.setText("");
            lbl02.setText("");
        }
    }

}
