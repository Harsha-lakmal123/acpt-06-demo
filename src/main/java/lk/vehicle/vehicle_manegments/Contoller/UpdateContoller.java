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
import java.sql.*;

public class UpdateContoller {
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
    void search(ActionEvent event) {
        int id = Integer.parseInt (txtid.getText ());
        try {
            //load to drive  class to ram
            Class.forName ("com.mysql.cj.jdbc.Driver");
            //load to drive  class to ram
            Connection connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/ls", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement ("select * from  vehicle where  id=?");
            preparedStatement.setObject (1, id);
            ResultSet resultSet = preparedStatement.executeQuery ();

            if (resultSet.next ()) {
                txtBrand.setText (resultSet.getString (2));
                txtModel.setText (resultSet.getString (3));
                txtQty.setText (resultSet.getString (4));
                txtPrice.setText (resultSet.getString (5));

            }


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException (e);
        }

    }

    @FXML
    void update(ActionEvent event) {
        int id = Integer.parseInt (txtid.getText ());
        String brand = txtBrand.getText ();
        String model = txtModel.getText ();
        String qty = txtQty.getText ();
        String price = txtPrice.getText ();

        txtid.setText ("");
        txtBrand.setText ("");
        txtModel.setText ("");
        txtQty.setText ("");
        txtPrice.setText ("");


        boolean e = VehicleModel.vehicleUpdate (new VehicleDto (id, brand, model, qty, price));





        if (e = true) {
            System.out.println (" Your Vehicle  updated Successfully ");
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle ("Success");
            alert.setHeaderText (null);
            alert.setContentText ("Vehicle Updated");
            alert.showAndWait ();

        } else {
            System.out.println (" Your Vehicle  updated UnSuccessfully");
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle ("Error");
            alert.setHeaderText (null);
            alert.setContentText ("Vehicle Not  Updated");
            alert.showAndWait ();
        }

    }

}
