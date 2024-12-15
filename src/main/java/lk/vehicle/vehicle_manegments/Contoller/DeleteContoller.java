package lk.vehicle.vehicle_manegments.Contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.vehicle.vehicle_manegments.Model.VehicleModel;
import java.io.IOException;

public class DeleteContoller {
    @FXML
    private TextField txtId;

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("/lk/vehicle/vehicle_manegments/option2-view.fxml"));
        Parent root = loader.load ();
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
        // Set the new scene
        stage.setScene (new Scene (root));
        stage.setTitle ("option2");
        stage.show ();
    }



    @FXML
    void delete(ActionEvent event) {
        int id = Integer.parseInt (txtId.getText ());
        txtId.clear ();
        VehicleModel.vehicleDelete (id);
    }
}
