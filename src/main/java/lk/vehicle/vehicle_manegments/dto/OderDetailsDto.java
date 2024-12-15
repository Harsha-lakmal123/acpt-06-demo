package lk.vehicle.vehicle_manegments.dto;

public class OderDetailsDto {
    private int vehicle_id;
    private int qty;
    private double price;


    public OderDetailsDto(double price, int qty, int id) {
        this.price = price;
        this.qty = qty;
        this.vehicle_id = id;

    }


    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

