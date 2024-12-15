package lk.vehicle.vehicle_manegments.Model;

import javafx.scene.control.Alert;
import lk.vehicle.vehicle_manegments.db.DBConenection;
import lk.vehicle.vehicle_manegments.dto.VehicleDto;
import lk.vehicle.vehicle_manegments.tm.VehicleTM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {

//Vehicle add method creative javaFx

    public static boolean vehicleAdd(VehicleDto vehicleDto) {
        try {
            Connection connection = DBConenection.getDBConnection ().getConnection ();

            PreparedStatement preparedStatement = connection.prepareStatement ("INSERT INTO vehicle VALUES (?,?,?,?,?)");

            preparedStatement.setInt (1, vehicleDto.getId ());
            preparedStatement.setString (2, vehicleDto.getBrand ());
            preparedStatement.setString (3, vehicleDto.getModel ());
            preparedStatement.setInt (5, vehicleDto.getQty ());
            preparedStatement.setDouble (4, vehicleDto.getPrice ());

            int e = preparedStatement.executeUpdate ();

            return e > 0;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException (e);
        }
    }

    //vehicle update method creative with the javaFx

    public static boolean vehicleUpdate(VehicleDto vehicleDto) {
        try {
            Connection connection = DBConenection.getDBConnection ().getConnection ();
            PreparedStatement preparedStatement = connection.prepareStatement ("update vehicle set brand =? , model =? , qty =? , price =? where id =?;");
            preparedStatement.setObject (1, vehicleDto.getBrand ());
            preparedStatement.setObject (2, vehicleDto.getModel ());
            preparedStatement.setObject (3, vehicleDto.getQty ());
            preparedStatement.setObject (4, vehicleDto.getPrice ());
            preparedStatement.setObject (5, vehicleDto.getId ());
            int i = preparedStatement.executeUpdate ();
            return i > 0;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException (e);
        }

    }

    //vehicle delete in the method creative with javaFx

    public static void vehicleDelete(int id) {
        System.out.println ("Is the"+id);
        try {
            Connection connection = DBConenection.getDBConnection ().getConnection ();
            PreparedStatement preparedStatement = connection.prepareStatement ("delete from vehicle where id =?;");
            preparedStatement.setInt (1, id);
            int e = preparedStatement.executeUpdate ();
            if (e > 0) {
                System.out.println ("vehicle Delete Successfully ");
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle ("Delete  is the  Vehicle");
                alert.setHeaderText (null);
                alert.setContentText (" Delete Vehicle Successfully");
                alert.showAndWait ();
            } else {
                System.out.println ("vehicle Delete UnSuccessfully ");
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle ("Delete is the  Vehicle");
                alert.setHeaderText (null);
                alert.setContentText ("Delete Vehicle UnSuccessfully");
                alert.showAndWait ();
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException (e);
        }

    }
    //load all in method creative with javafx
    public static ArrayList<VehicleTM> vehicleLoad() {

        ArrayList<VehicleTM> tms = null;
        ResultSet resultSet = null;
        try {


            Connection connection = DBConenection.getDBConnection ().getConnection ();

            PreparedStatement preparedStatement = connection.prepareStatement ("select * from vehicle");
            resultSet = preparedStatement.executeQuery ();

            tms = new ArrayList<> ();
            while (resultSet.next ()) {
                tms.add (new VehicleTM (resultSet.getInt (1), resultSet.getString (2), resultSet.getString (3), resultSet.getInt (4), resultSet.getDouble (5)));
            }
            //configure in table im javFx


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace ();
        }

        return tms;

    }



}