package lk.ijse.Hibernate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
    @author : Hasii-boy
*/
public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
//        Room room = new Room();
//        room.setRId("0001");
//        room.setType("A/c");
//        room.setKeyMoney("5000");
//        room.setRoomQty(10);
//
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//
////        session.save(room);
//
//        transaction.commit();
//        session.close();
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
//        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/Hibernate/view/login-form.fxml"));
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/Hibernate/view/dashboard-form.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("HOSTEL RESERVATION SYSTEM");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}