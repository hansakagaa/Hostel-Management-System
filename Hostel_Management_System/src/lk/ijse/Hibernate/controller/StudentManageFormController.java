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
import lk.ijse.Hibernate.bo.custom.StudentBO;
import lk.ijse.Hibernate.dto.StudentDTO;
import lk.ijse.Hibernate.view.tm.StudentTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author : Hasii-boy
 **/
public class StudentManageFormController {
    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXDatePicker txtBoDate;
    @FXML
    private JFXTextField txtGender;
    @FXML
    private TableView<StudentTM> tblStudent;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnSaveOrUpdate;

    public void initialize(){
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("sId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        initUI();

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, student) -> {
            btnDelete.setDisable(student == null);
//            btnSaveOrUpdate.setText(student != null ? "Update Student" : "Save Student");
            btnSaveOrUpdate.setDisable(student == null);

            if (student != null) {
                txtId.setText(student.getSId());
                txtName.setText(student.getName());
                txtAddress.setText(student.getAddress());
                txtContact.setText(student.getContact());
                txtBoDate.setValue(student.getDateOfBirth());
                txtGender.setText(student.getGender());

                setDisable(false, txtId,txtName,txtAddress,txtContact,txtGender);
                txtBoDate.setDisable(false);
            }
        });

        txtGender.setOnAction(event -> btnSaveOrUpdate.fire());
//        loadAllStudent();
    }

    private void loadAllStudent() {
        tblStudent.getItems().clear();
        try {

            ArrayList<StudentDTO> allStudents = studentBO.getAllStudent();

            for (StudentDTO dto : allStudents) {
                tblStudent.getItems().add(new StudentTM(dto.getSId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDateOfBirth(), dto.getGender()));
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

    private void clear(JFXTextField... field){
        for (JFXTextField textField : field) {
            textField.clear();
        }
    }

    private void initUI() {
        txtBoDate.setDisable(true);
        txtBoDate.setValue(LocalDate.now());
        clear(txtId,txtName,txtAddress,txtContact,txtGender);
        setDisable(true, txtId,txtName,txtAddress,txtContact,txtGender);
        txtId.setEditable(false);
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
    private void studentSaveOrUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        LocalDate bod = txtBoDate.getValue();
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

        if (btnSaveOrUpdate.getText().equalsIgnoreCase("Save Student")) {
            try {
                if (studentBO.exitsStudent(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                boolean student = studentBO.saveStudent(new StudentDTO(id, name, address, contact, bod, gender));
                if (student) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student has been saved successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student has not been Saved successfully").show();
                }

                tblStudent.getItems().add(new StudentTM(id, name, address, contact, bod, gender));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "" + e.getMessage()).show();
            }


        } else {
            try {
                if (!studentBO.exitsStudent(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such student associated with the id " + id).show();
                }
                boolean student = studentBO.updateStudent(new StudentDTO(id, name, address, contact, bod, gender));
                if (student) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student has been updated successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student has not been updated successfully").show();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, ""+e.getMessage()).show();
            }

            StudentTM selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
            selectedStudent.setName(name);
            selectedStudent.setAddress(address);
            selectedStudent.setContact(contact);
            selectedStudent.setDateOfBirth(bod);
            selectedStudent.setGender(gender);
            tblStudent.refresh();
        }
    }

    @FXML
    private void studentDeleteOnAction(ActionEvent actionEvent) {
        String id = tblStudent.getSelectionModel().getSelectedItem().getSId();
        try {
            if (!studentBO.exitsStudent(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }

            boolean student = studentBO.deleteStudent(id);

            if (student){
                new Alert(Alert.AlertType.ERROR,"Student has been Deleted successfully").show();
            }

            tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
            tblStudent.getSelectionModel().clearSelection();
            initUI();

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
