package lk.vehicle.vehicle_manegments.dto;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DateFormat;
import java.util.Date;

public class PrintDto {
    private int id;
    private String brand;
    private String model;
    private double price;
    private double totalPrice;
    private double qty;
    private String date;

    public PrintDto(Label txtSubTotal, TextField txtbrand, TextField txtModel, TextField txtPrice, DateFormat dateFormat, TextField userId, double subTotal) {
        this.id = Integer.parseInt(userId.getText());
        this.brand = txtbrand.getText();
        this.model = txtModel.getText();
        this.price = Double.parseDouble(txtPrice.getText());
        this.totalPrice = subTotal;
        this.qty = Double.parseDouble(txtSubTotal.getText());
        this.date = dateFormat.format(new Date ());

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
