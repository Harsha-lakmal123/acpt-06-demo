package lk.vehicle.vehicle_manegments.dto;

import javafx.scene.control.TextField;

import java.util.ArrayList;

public class OderDto {
    private  String brand ;
    private String date;

    private double subtotal;

    private ArrayList<OderDetailsDto> orderDetailsDto;

    public OderDto(String date, double subtotal, ArrayList<OderDetailsDto> orderDetailsDro) {
        this.date = date;
        this.subtotal = subtotal;
        this.orderDetailsDto = orderDetailsDro;
    }

    public OderDto(TextField txtbrand, double subTotal, ArrayList<OderDetailsDto> orderDetailsDto, ArrayList<OderDetailsDto> orderDetailsDto1, String formattedDate) {
        this.date = formattedDate;
        this.subtotal = subTotal;
        this.orderDetailsDto = orderDetailsDto;
        this.orderDetailsDto = orderDetailsDto1;
        this.setBrand (txtbrand.getText());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public ArrayList<OderDetailsDto> getOrderDetailsDro() {
        return orderDetailsDto;
    }

    public void setOrderDetailsDro(ArrayList<OderDetailsDto> orderDetailsDro) {
        this.orderDetailsDto = orderDetailsDro;
    }

    @Override
    public String toString() {
        return "OderDto{" +
                "subtotal=" + subtotal +
                ", date='" + date + '\'' +
                ", orderDetailsDto=" + orderDetailsDto +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
