module lk.vehicle.vehicle_manegments {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.apache.pdfbox;




    opens lk.vehicle.vehicle_manegments to javafx.fxml;
    exports lk.vehicle.vehicle_manegments;
    exports lk.vehicle.vehicle_manegments.Contoller;
    opens lk.vehicle.vehicle_manegments.Contoller to javafx.fxml;
    exports lk.vehicle.vehicle_manegments.tm;
    opens lk.vehicle.vehicle_manegments.tm to javafx.fxml;
}