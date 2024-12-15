package lk.vehicle.vehicle_manegments.Contoller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.vehicle.vehicle_manegments.Model.OrderModel;
import lk.vehicle.vehicle_manegments.dto.OderDetailsDto;
import lk.vehicle.vehicle_manegments.dto.OderDto;
import lk.vehicle.vehicle_manegments.dto.PrintDto;
import lk.vehicle.vehicle_manegments.tm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OderContoller implements Initializable {

    @FXML
    private TextField CustomerQty;

    @FXML
    private TextField UserId;

    @FXML
    private TextField onHandQty;

    @FXML
    private TableView<ItemTM> tbleOder;

    @FXML
    private TextField txtbrand;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private Label txtSubTotal;

    double subTotal;

    private List<ItemTM> itemTMs;
    ArrayList<OderDetailsDto> orderDetailsDto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itemTMs = new ArrayList<> ();
        tbleOder.getColumns ().get (0).setCellValueFactory (new PropertyValueFactory<> ("Brand"));
        tbleOder.getColumns ().get (1).setCellValueFactory (new PropertyValueFactory<> ("Model"));
        tbleOder.getColumns ().get (2).setCellValueFactory (new PropertyValueFactory<> ("qty"));
        tbleOder.getColumns ().get (3).setCellValueFactory (new PropertyValueFactory<> ("UnitPrice"));
        tbleOder.getColumns ().get (4).setCellValueFactory (new PropertyValueFactory<> ("total"));
        tbleOder.setItems (FXCollections.observableArrayList (itemTMs));
       orderDetailsDto = new ArrayList<>();

    }
    @FXML
    void Back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("/lk/vehicle/vehicle_manegments/option1-view.fxml"));
        Parent root = loader.load ();
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
        // Set the new scene
        stage.setScene (new Scene (root));
        stage.setTitle ("option2");
        stage.show ();

    }



    @FXML
    void addCard(ActionEvent event) {
        int qty = Integer.parseInt (CustomerQty.getText ());
        int id = Integer.parseInt (UserId.getText ());
        double price = Double.parseDouble (txtPrice.getText ());
        String brand = txtbrand.getText ();
        String model = txtModel.getText ();
        double total = price * qty;
        itemTMs.add (new ItemTM (brand, model, id, qty, total));
        tbleOder.setItems (FXCollections.observableArrayList (itemTMs));
        subTotal+=total;
        txtSubTotal.setText (String.valueOf (subTotal));
        orderDetailsDto.add (new OderDetailsDto (price,qty,id));

    }

    @FXML
    void cancel(ActionEvent event) {
        System.exit (0);

    }

    @FXML
    void placeOrder(ActionEvent event) {
        DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd");
        Date date = new Date ();
        String formattedDate = dateFormat.format (date);


//        ArrayList<OderDetailsDto> orderDetailsDro;

        try {

          boolean i =  OrderModel.placholder (new OderDto (formattedDate,subTotal,orderDetailsDto));


          if (true){

              Alert alert  =  new Alert (Alert.AlertType.CONFIRMATION);
              alert.setTitle ("Place Order ");
              alert.setHeaderText(" Successfully  ");
              alert.setContentText("Hy User Your Order Successfully Place Order ");
              alert.showAndWait();
              System.out.println ("Save");

          }else {
              Alert alert  =  new Alert (Alert.AlertType.ERROR);
              alert.setTitle ("Place Order ");
              alert.setHeaderText(" UnSuccessfully  ");
              alert.setContentText("Hy User Your Order UnSuccessfully Place Order ");
              alert.showAndWait();
              System.out.println ("Not Save");
          }
        } catch (SQLException e) {
            throw new RuntimeException (e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException (e);
        }


        try {
            OrderModel.print (new PrintDto (txtSubTotal,txtbrand,txtModel,txtPrice,dateFormat,UserId,subTotal));
        } catch (IOException e) {
            throw new RuntimeException (e);
        }

    }

    @FXML
    void searchUser(ActionEvent event) {
        int id = Integer.parseInt (UserId.getText ());
        try {

            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/ls", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement ("select * from  vehicle where  id=?");
            preparedStatement.setObject (1, id);
            ResultSet resultSet = preparedStatement.executeQuery ();


            if (resultSet.next ()) {
                txtbrand.setText (resultSet.getString (2));
                txtModel.setText (resultSet.getString (3));
                onHandQty.setText (resultSet.getString (4));
                txtPrice.setText (resultSet.getString (5));

            } else {
                System.out.println ("Your Id is not found");
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle ("Error");
                alert.setHeaderText (null);
                alert.setContentText ("Your Id is not found & Try Again");
                alert.showAndWait ();
            }


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException (e);
        }

    }

}

