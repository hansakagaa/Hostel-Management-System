package lk.ijse.Hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Hibernate.bo.BOFactory;
import lk.ijse.Hibernate.bo.BOType;
import lk.ijse.Hibernate.bo.custom.DashboardBO;
import lk.ijse.Hibernate.dto.ReservationDTO;
import lk.ijse.Hibernate.dto.RoomDTO;
import lk.ijse.Hibernate.dto.StudentDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author : Hasii-boy
 **/
public class DashboardFormController {
    private final DashboardBO dashboardBO = BOFactory.getInstance().getBO(BOType.DASHBOARD);
    @FXML
    private AnchorPane root;
    @FXML
    private ComboBox<String> cmdStudent;
    @FXML
    private Label lblStudent;
    @FXML
    private JFXTextField txtRes_id;
    @FXML
    private ComboBox<String> cmdRoom_type;
    @FXML
    private Label lblReservation;
    @FXML
    private JFXTextField txtStatus;
    @FXML
    private Label lblDate_Time;
    @FXML
    private JFXButton btnReservation;
    @FXML
    private Label lbl01;
    @FXML
    private Label lbl02;


    public void initialize(){
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        enableOrDisableReservationButton();

        cmdStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, student) -> {
            enableOrDisableReservationButton();

            if (student != null) {
                try {
                    boolean exits = dashboardBO.exitsStudent(student + "");

                    if (!exits) {
                        new Alert(Alert.AlertType.ERROR, "There is no such Student associated with the id " + student + "").show();
                    }

                    StudentDTO dto = dashboardBO.findStudent(student + "");
                    lblStudent.setText(dto.getName()+" | "+dto.getAddress()+" | "+dto.getContact()+" | "+dto.getDateOfBirth()+" | "+dto.getGender());

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            } else {
                lblStudent.setText("");
            }
        });

        cmdRoom_type.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, room) -> {
            enableOrDisableReservationButton();

            if (room != null) {
                try {
                    boolean exits = dashboardBO.exitsRoom(room + "");

                    if (!exits) {
                        new Alert(Alert.AlertType.ERROR, "There is no such Room associated with the id " + room + "").show();
                    }

                    RoomDTO dto = dashboardBO.findRoom(room + "");
                    lblReservation.setText(dto.getType()+" | "+dto.getKeyMoney()+" | "+dto.getRoomQty());

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            } else {
                lblReservation.setText("");
            }
        });

//        loadAllStudentId();
//        loadAllRoomType();
        lordDateAndTime();
    }

    private void enableOrDisableReservationButton() {
        btnReservation.setDisable(cmdStudent.getSelectionModel().getSelectedItem() == null && cmdRoom_type.getSelectionModel().getSelectedItem() == null && txtRes_id.getText() == null);
    }

    private void loadAllStudentId() {
        try {
            ArrayList<StudentDTO> dtoS = dashboardBO.getAllStudent();
            for (StudentDTO dto : dtoS) {
                cmdStudent.getItems().add(dto.getSId());
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load student ids").show();
        }
    }

    private void loadAllRoomType() {
        try {
            ArrayList<RoomDTO> dtoS = dashboardBO.getAllRoom();
            for (RoomDTO dto : dtoS) {
                cmdStudent.getItems().add(dto.getRId());
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load room ids").show();
        }
    }

    private void clear(JFXTextField... field){
        for (JFXTextField textField : field) {
            textField.clear();
        }
    }

    private void lordDateAndTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy - MM - dd           HH : mm : ss");
            lblDate_Time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    private void saveReservationOnAction(ActionEvent actionEvent){
        String id = txtRes_id.getText();
        String studentId = cmdStudent.getValue();
        String roomId = cmdRoom_type.getValue();
        try {
            boolean reserve = dashboardBO.reserveStudent(new ReservationDTO(id, LocalDate.now(), studentId, roomId, txtStatus.getText()));
            if (reserve) {
                new Alert(Alert.AlertType.INFORMATION, "Reservation has been saved successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Reservation has not been saved successfully").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    private void cancelReservationOnAction(ActionEvent actionEvent) {
        cmdStudent.getSelectionModel().clearSelection();
        cmdRoom_type.getSelectionModel().clearSelection();
        lblStudent.setText("");
        lblReservation.setText("");
        clear(txtRes_id,txtStatus);
    }

    @FXML
    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();

            Parent root = null;

            switch (button.getId()) {
                case "btnAddNew":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/Hibernate/view/student-add-new-form.fxml"));
                    break;
                case "btnRoom":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/Hibernate/view/room-manage-form.fxml"));
                    break;
                case "btnStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/Hibernate/view/student-manage-form.fxml"));
                    break;
                case "btnReserve":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/Hibernate/view/reserve-details-form.fxml"));
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
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
    private void playMouseExitAnimation(MouseEvent event) {
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
