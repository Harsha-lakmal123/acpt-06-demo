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
import lk.vehicle.vehicle_manegments.Model.VehicleModel;
import lk.vehicle.vehicle_manegments.dto.VehicleDto;

import java.io.IOException;


public class    SaveFromContoller {
    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtid;

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
    void save(ActionEvent event) {
        int id = Integer.parseInt (txtid.getText ());
        String brand = txtBrand.getText ();
        String model = txtModel.getText ();
        double price = Double.parseDouble (txtPrice.getText ());
        int qty = Integer.parseInt (txtQty.getText ());


        txtid.setText ("");
        txtBrand.setText ("");
        txtModel.setText ("");
        txtPrice.setText ("");
        txtQty.setText ("");


        boolean e = VehicleModel.vehicleAdd (new VehicleDto (id, brand, model, qty, price));

        if (e) {
            System.out.println ("vehicle add Successfully ");
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle ("Add is the  Vehicle");
            alert.setHeaderText (null);
            alert.setContentText ("Add Vehicle Successfully");
            alert.showAndWait ();

        } else {
            System.out.println ("vehicle add UnSuccessfully ");
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle ("Add is the  Vehicle");
            alert.setHeaderText (null);
            alert.setContentText ("Add Vehicle UnSuccessfully");
            alert.showAndWait ();
        }


    }
}
