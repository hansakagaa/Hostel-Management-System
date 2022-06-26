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
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Hibernate.view.tm.StudentTM;

import java.io.IOException;

/**
 * @author : Hasii-boy
 **/
public class StudentManageFormController {
    @FXML
    public AnchorPane root;
    @FXML
    public JFXTextField txtId;
    @FXML
    public JFXTextField txtName;
    @FXML
    public JFXTextField txtAddress;
    @FXML
    public JFXTextField txtContact;
    @FXML
    public JFXTextField txtBoDate;
    @FXML
    private JFXTextField txtGender;
    @FXML
    public TableView<StudentTM> tblStudent;
    @FXML
    public JFXButton btnDelete;
    @FXML
    public JFXButton btnSaveOrUpdate;


    @FXML
    public void navigateToHome(MouseEvent event) throws IOException {
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/Hibernate/view/dashboard-form.fxml"))));
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    @FXML
    public void studentSaveOrUpdateOnAction(ActionEvent actionEvent) {

    }

    @FXML
    public void studentDeleteOnAction(ActionEvent actionEvent) {

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
