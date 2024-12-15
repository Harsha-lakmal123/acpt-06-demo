package lk.vehicle.vehicle_manegments.Contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class option1Contoller {
    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("/lk/vehicle/vehicle_manegments/login-view.fxml"));
        Parent root = loader.load ();
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
        // Set the new scene
        stage.setScene (new Scene (root));
        stage.setTitle ("option2");
        stage.show ();
    }
    @FXML
    void next(ActionEvent event) {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle ("Confirmation");
        alert.setHeaderText (null);
        alert.setContentText ("Please it option not creative ");
        alert.showAndWait ();
    }
    @FXML
    void service(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("/lk/vehicle/vehicle_manegments/oder-view.fxml"));
        Parent root = loader.load ();
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
        // Set the new scene
        stage.setScene (new Scene (root));
        stage.setTitle ("option2");
        stage.show ();
    }
    @FXML
    void vehicle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("/lk/vehicle/vehicle_manegments/option2-view.fxml"));
        Parent root = loader.load ();
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
        // Set the new scene
        stage.setScene (new Scene (root));
        stage.setTitle ("option2");
        stage.show ();
    }
}
