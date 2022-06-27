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
import lk.ijse.Hibernate.bo.custom.RoomBO;
import lk.ijse.Hibernate.dto.RoomDTO;
import lk.ijse.Hibernate.view.tm.RoomTM;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Hasii-boy
 **/
public class RoomManageFormController {
    private final RoomBO roomBO = BOFactory.getInstance().getBO(BOType.ROOM);
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtRoomTypeId;
    @FXML
    private JFXTextField txtKeyMoney;
    @FXML
    private JFXTextField txtRoomQty;
    @FXML
    private JFXTextField txtRoomType;
    @FXML
    private JFXButton btnSaveOrUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<RoomTM> tblRoom;

    public void initialize(){
        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("rId"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomQty"));

        initUI();

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, room) -> {
            btnDelete.setDisable(room == null);
            btnSaveOrUpdate.setText(room != null ? "Update Room" : "Save Room");
            if(room != null)  setDisable(false,txtRoomType); else setEditable(true,txtRoomType);
            btnSaveOrUpdate.setDisable(room == null);

            if (room != null) {
                txtRoomTypeId.setText(room.getRId());
                txtRoomType.setText(room.getType());
                txtKeyMoney.setText(room.getKeyMoney());
                txtRoomQty.setText(room.getRoomQty()+"");

                setDisable(false, txtRoomTypeId,txtRoomType,txtKeyMoney,txtRoomQty);
            }
        });

        txtRoomQty.setOnAction(event -> btnSaveOrUpdate.fire());
        loadAllRoom();
    }

    private void loadAllRoom() {
        tblRoom.getItems().clear();
        try {

            ArrayList<RoomDTO> allRooms = roomBO.getAllRoom();

            for (RoomDTO dto : allRooms) {
                tblRoom.getItems().add(new RoomTM(dto.getRId(), dto.getType(), dto.getKeyMoney(), dto.getRoomQty()));
            }

        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
            textField.setDisable(b);
        }
    }

    private void clear(JFXTextField... field){
        for (JFXTextField textField : field) {
            textField.clear();
        }
    }

    private void initUI() {
        clear(txtRoomTypeId,txtRoomType,txtKeyMoney,txtRoomQty);
        setDisable(true, txtRoomTypeId,txtRoomType,txtKeyMoney,txtRoomQty);
        setEditable(false, txtRoomTypeId);
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
    private void roomSaveOrUpdateRoomOnAction(ActionEvent actionEvent) {
        String id = txtRoomTypeId.getText();
        String type = txtRoomType.getText();
        String keyMoney = txtKeyMoney.getText();
        String qty = txtRoomQty.getText();

        if (!keyMoney.matches("^([0-9]+)$|([0-9]+[.][0-9]{2})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Key Money").show();
            txtKeyMoney.requestFocus();txtKeyMoney.selectAll();
            return;
        }else
        if (!qty.matches("^\\b+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room Qty").show();
            txtRoomQty.requestFocus();txtRoomQty.selectAll();
            return;
        }

        if (btnSaveOrUpdate.getText().equalsIgnoreCase("Save Room")) {
            try {
                if (roomBO.exitsRoom(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                roomBO.saveRoom(new RoomDTO(id, type, keyMoney, Integer.parseInt(qty)));

                tblRoom.getItems().add(new RoomTM(id, type, keyMoney, Integer.parseInt(qty)));
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "" + e.getMessage()).show();
            }
        } else {
            try {
                if (!roomBO.exitsRoom(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such room associated with the id " + id).show();
                }
                roomBO.updateRoom(new RoomDTO(id, type, keyMoney, Integer.parseInt(qty)));
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, ""+e.getMessage()).show();
            }

            RoomTM selectedRoom = tblRoom.getSelectionModel().getSelectedItem();
            selectedRoom.setType(type);
            selectedRoom.setKeyMoney(keyMoney);
            selectedRoom.setRoomQty(Integer.parseInt(qty));
            tblRoom.refresh();
        }
    }

    @FXML
    private void roomDeleteRoomOnAction(ActionEvent actionEvent) {
        String id = tblRoom.getSelectionModel().getSelectedItem().getRId();
        try {
            if (!roomBO.exitsRoom(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such room associated with the id " + id).show();
            }

            boolean room = roomBO.deleteRoom(id);

            if (room){
                new Alert(Alert.AlertType.ERROR,"Room has been Deleted successfully").show();
            }

            tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
            tblRoom.getSelectionModel().clearSelection();
            initUI();

        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,""+e.getMessage()).show();
        }
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
