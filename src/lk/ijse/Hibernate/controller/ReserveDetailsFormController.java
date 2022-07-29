package lk.ijse.Hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Hibernate.bo.BOFactory;
import lk.ijse.Hibernate.bo.BOType;
import lk.ijse.Hibernate.bo.custom.ReservationBO;
import lk.ijse.Hibernate.dto.ReservationDTO;
import lk.ijse.Hibernate.view.tm.ReservationTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author : Hasii-boy
 **/
public class ReserveDetailsFormController {
    private final ReservationBO reservationBO = BOFactory.getInstance().getBO(BOType.RESERVATION);
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtDate;
    @FXML
    private JFXTextField txtStatus;
    @FXML
    private JFXTextField txtStudentId;
    @FXML
    private JFXTextField txtReserveId;
    @FXML
    private JFXTextField txtRoomTypeId;
    @FXML
    private TableView<ReservationTM> tblReserve;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnSaveOrUpdate;

    public void initialize(){
        tblReserve.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("resId"));
        tblReserve.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblReserve.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sId"));
        tblReserve.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("rId"));
        tblReserve.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));

        initUI();

        tblReserve.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, reserve) -> {
//            btnDelete.setDisable(reserve == null);
//            btnSaveOrUpdate.setText(reserve != null ? "Update Reservation" : "Save Reservation");
            btnSaveOrUpdate.setDisable(reserve == null);

            if (reserve != null) {
                txtReserveId.setText(reserve.getResId());
                txtDate.setText(reserve.getDate().toString());
                txtStudentId.setText(reserve.getSId());
                txtRoomTypeId.setText(reserve.getRId());
                txtStatus.setText(reserve.getStatus());

                setDisable(false, txtReserveId, txtDate, txtStudentId, txtRoomTypeId, txtStatus);
            }
        });

        txtStatus.setOnAction(event -> btnSaveOrUpdate.fire());
        loadAllReservation();
    }

    private void loadAllReservation() {
        tblReserve.getItems().clear();
        try {

            ArrayList<ReservationDTO> allReserve = reservationBO.getAllReservation();

            for (ReservationDTO dto : allReserve) {
                tblReserve.getItems().add(new ReservationTM(dto.getResId(), dto.getDate(), dto.getSId(), dto.getRId(), dto.getStatus()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, ""+e.getMessage()).show();
        }
    }

    private void setDisable(boolean b, JFXTextField... field){
        for (JFXTextField textField : field) {
            textField.setDisable(b);
        }
    }

    private void setEditable(boolean b, JFXTextField... field){
        for (JFXTextField textField : field) {
            textField.setEditable(b);
        }
    }

    private void clear(JFXTextField... field){
        for (JFXTextField textField : field) {
            textField.clear();
        }
    }

    private void initUI() {
        clear(txtReserveId, txtDate, txtStudentId, txtRoomTypeId, txtStatus);
        setDisable(true, txtReserveId, txtDate, txtStudentId, txtRoomTypeId, txtStatus);
        setEditable(false, txtReserveId,txtDate);
        btnSaveOrUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/Hibernate/view/dashboard-form.fxml"))));
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    @FXML
    private void reserveSaveOrUpdateOnAction(ActionEvent actionEvent) {
        String resId = txtReserveId.getText();
        LocalDate date = LocalDate.parse(txtDate.getText());
        String sId = txtStudentId.getText();
        String rId = txtRoomTypeId.getText();
        String status = txtStatus.getText();
        try {
            if (reservationBO.exitsRoomTypeId(resId)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Room Type ID").show();
                txtRoomTypeId.requestFocus();txtRoomTypeId.selectAll();
                return;
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "" + e.getMessage()).show();
        }


        if (btnSaveOrUpdate.getText().equalsIgnoreCase("Save Reservation")) {
            try {
                if (reservationBO.exitsReservation(resId)) {
                    new Alert(Alert.AlertType.ERROR, resId + " already exists").show();
                }

                boolean reservation = reservationBO.saveReservation(new ReservationDTO(resId, date, sId, rId, status));
                if (reservation) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Reservation has been saved successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Reservation has not been Saved successfully").show();
                }

                tblReserve.getItems().add(new ReservationTM(resId, date, sId, rId, status));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "" + e.getMessage()).show();
            }
        } else {
            try {
                if (!reservationBO.exitsReservation(resId)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such reserve associated with the id " + sId).show();
                }
                boolean reservation = reservationBO.updateReservation(new ReservationDTO(resId, date, sId, rId, status));
                if (reservation) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Reservation has been updated successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Reservation has not been updated successfully").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, ""+e.getMessage()).show();
            }

            ReservationTM selectedReservation = tblReserve.getSelectionModel().getSelectedItem();
            selectedReservation.setDate(date);
            selectedReservation.setSId(sId);
            selectedReservation.setRId(rId);
            selectedReservation.setStatus(status);
            tblReserve.refresh();
        }
    }

    @FXML
    private void reserveDeleteOnAction(ActionEvent actionEvent) {
//        String id = tblReserve.getSelectionModel().getSelectedItem().getRId();
//        try {
//            if (!reservationBO.exitsReservation(id)) {
//                new Alert(Alert.AlertType.ERROR, "There is no such reservation associated with the id " + id).show();
//            }
//            boolean reserve = reservationBO.deleteReservation(id);
//            if (reserve){
//                new Alert(Alert.AlertType.ERROR,"Reservation has been Deleted successfully").show();
//            }
//            tblReserve.getItems().remove(tblReserve.getSelectionModel().getSelectedItem());
//            tblReserve.getSelectionModel().clearSelection();
//            initUI();
//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR,""+e.getMessage()).show();
//        }
    }

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
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
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof Button){
            Button button = (Button) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), button);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            button.setEffect(null);
        }
        if (event.getSource() instanceof ImageView){
            ImageView image = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), image);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            image.setEffect(null);
        }
    }
}
