package lk.vehicle.vehicle_manegments.Contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginContoller {
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    void cancel(ActionEvent event) {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle ("Confirmation");
        alert.setHeaderText (null);
        alert.setContentText ("Are you sure you want to exit?");
        alert.showAndWait ();
        System.exit (0);
    }
    @FXML
    void login(ActionEvent event) throws IOException {
        String name = txtName.getText ();
        String password = txtPassword.getText ();

        if (name.equals ("Harsha") && password.equals ("123")) {
            FXMLLoader loader = new FXMLLoader (getClass ().getResource ("/lk/vehicle/vehicle_manegments/option1-view.fxml"));
            Parent root = loader.load ();
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
            // Set the new scene
            stage.setScene (new Scene (root));
            stage.setTitle ("option2");
            stage.show ();

        } else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle ("Error");
            alert.setHeaderText (null);
            alert.setContentText ("Please enter a valid username and password");
            alert.showAndWait ();

        }

    }

}
