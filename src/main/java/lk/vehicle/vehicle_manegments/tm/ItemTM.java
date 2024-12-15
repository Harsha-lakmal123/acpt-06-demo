package lk.vehicle.vehicle_manegments.tm;

public class ItemTM {
      private String Brand;
      private String Model;
      private int qty;
      private double UnitPrice;
      private double total;

    public ItemTM(String brand, String model, int qty, double unitPrice, double total) {
        Brand = brand;
        Model = model;
        this.qty = qty;
        UnitPrice = unitPrice;
        this.total = total;
    }



    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    }





