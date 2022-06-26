package lk.ijse.Hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Hibernate.bo.BOFactory;
import lk.ijse.Hibernate.bo.BOType;
import lk.ijse.Hibernate.bo.custom.StudentBO;
import lk.ijse.Hibernate.dto.StudentDTO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author : Hasii-boy
 **/
public class StudentAddNewFormController {
    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXDatePicker txtBirthday;
    @FXML
    private JFXTextField txtGender;
    @FXML
    private JFXButton btnSave;

    public void initialize(){
        txtId.setOnAction(event -> txtName.requestFocus());
        txtName.setOnAction(event -> txtAddress.requestFocus());
        txtAddress.setOnAction(event -> txtContact.requestFocus());
        txtContact.setOnAction(event -> txtBirthday.requestFocus());
        txtBirthday.setOnAction(event -> txtGender.requestFocus());
    }

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/Hibernate/view/dashboard-form.fxml"))));
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    @FXML
    private void saveStudentOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        LocalDate bod = txtBirthday.getValue();
        String gender = txtGender.getText();

        if (!name.matches("^[A-z ]{5,50}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Name").show();
            txtName.requestFocus();txtName.selectAll();
            return;
        }else
        if (!address.matches("^[A-z \\d]{5,100}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Address").show();
            txtAddress.requestFocus();txtAddress.selectAll();
            return;
        }else
        if (!contact.matches("^((0)[0-9]{9})$|^((\\+947)[0-9]{8})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Contact").show();
            txtContact.requestFocus();txtContact.selectAll();
            return;
        }else
        if (!gender.matches("^(Male|Female)$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Gender").show();
            txtGender.requestFocus();txtGender.selectAll();
            return;
        }

        try {
            boolean exits = studentBO.exitsStudent(id);
            if (exits){
                new Alert(Alert.AlertType.ERROR, "This Student has been already exits").show();
            }
            boolean student = studentBO.saveStudent(new StudentDTO(id, name, address, contact, bod, gender));
            if (student) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student has been saved successfully").show();
                navigateToHome(null);
            } else {
                new Alert(Alert.AlertType.ERROR, "Student has not been Saved successfully").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof Button){
            Button button = (Button) event.getSource();
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
        if (event.getSource() instanceof ImageView){
            ImageView image = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), image);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            image.setEffect(glow);
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
            button.setEffect(null);
        }
        if (event.getSource() instanceof ImageView){
            ImageView image = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), image);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            image.setEffect(null);
            image.setEffect(null);
        }
    }
}
