package lk.vehicle.vehicle_manegments.Contoller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.vehicle.vehicle_manegments.Model.VehicleModel;
import lk.vehicle.vehicle_manegments.db.DBConenection;
import lk.vehicle.vehicle_manegments.tm.VehicleTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadContoller implements Initializable {
    @FXML
    private TableView<VehicleTM> tbleVehicle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = DBConenection.getDBConnection ().getConnection ();
            PreparedStatement preparedStatement = connection.prepareStatement ("select * from vehicle");
            ResultSet resultSet = preparedStatement.executeQuery ();
            ArrayList<VehicleTM> tms = new ArrayList<> ();
            while (resultSet.next ()) {
                tms.add (new VehicleTM (resultSet.getInt (1), resultSet.getString (2), resultSet.getString (3), resultSet.getInt (4), resultSet.getDouble (5)));
            }
            //configure in table im javFx
            tbleVehicle.getColumns ().get (0).setCellValueFactory (new PropertyValueFactory<> ("id"));
            tbleVehicle.getColumns ().get (1).setCellValueFactory (new PropertyValueFactory<> ("brand"));
            tbleVehicle.getColumns ().get (2).setCellValueFactory (new PropertyValueFactory<> ("model"));
            tbleVehicle.getColumns ().get (3).setCellValueFactory (new PropertyValueFactory<> ("qty"));
            tbleVehicle.getColumns ().get (4).setCellValueFactory (new PropertyValueFactory<> ("price"));
            tbleVehicle.setItems (FXCollections.observableArrayList (tms));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace ();
        }
    }
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
    void load(ActionEvent event) {
        ArrayList<VehicleTM> tms = VehicleModel.vehicleLoad ();
        tbleVehicle.getColumns ().get (0).setCellValueFactory (new PropertyValueFactory<> ("id"));
        tbleVehicle.getColumns ().get (1).setCellValueFactory (new PropertyValueFactory<> ("brand"));
        tbleVehicle.getColumns ().get (2).setCellValueFactory (new PropertyValueFactory<> ("model"));
        tbleVehicle.getColumns ().get (3).setCellValueFactory (new PropertyValueFactory<> ("qty"));
        tbleVehicle.getColumns ().get (4).setCellValueFactory (new PropertyValueFactory<> ("price"));
        tbleVehicle.setItems (FXCollections.observableArrayList (tms));
    }


}



