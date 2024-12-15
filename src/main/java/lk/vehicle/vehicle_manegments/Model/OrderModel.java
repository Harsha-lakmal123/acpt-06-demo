package lk.vehicle.vehicle_manegments.Model;

import javafx.scene.control.Alert;
import lk.vehicle.vehicle_manegments.db.DBConenection;
import lk.vehicle.vehicle_manegments.dto.OderDetailsDto;
import lk.vehicle.vehicle_manegments.dto.OderDto;
import lk.vehicle.vehicle_manegments.dto.PrintDto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;

public class OrderModel {
    public static boolean placholder(OderDto oderDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConenection.getDBConnection ().getConnection ();
        connection.setAutoCommit (false);

        PreparedStatement preparedStatement1 = connection.prepareStatement ("insert into orders(order_date,amount)values(?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement1.setObject (1, oderDto.getDate ());
        preparedStatement1.setObject (2, oderDto.getSubtotal ());

        int ordersSave = preparedStatement1.executeUpdate ();

        if (ordersSave < 0) {

            ResultSet generatedKeysResultSet = preparedStatement1.getGeneratedKeys ();
            int id = generatedKeysResultSet.getInt (1);

            for (OderDetailsDto dto : oderDto.getOrderDetailsDro ()) {

                PreparedStatement preparedStatement2 = connection.prepareStatement ("insert into order_details(or_id,veh_id,qty,price) values (?,?,?,?)");
                preparedStatement2.setObject (1, id);
                preparedStatement2.setObject (2, dto.getVehicle_id ());
                preparedStatement2.setObject (3, dto.getQty ());
                preparedStatement2.setObject (4, dto.getPrice ());

                int orderDsSave = preparedStatement2.executeUpdate ();

                if (orderDsSave > 0) {
                    PreparedStatement preparedStatement3 = connection.prepareStatement ("update vehicle set qty= qty-? where id=?");
                    preparedStatement3.setObject (1, dto.getQty ());
                    preparedStatement3.setObject (2, id);

                    int V_update = preparedStatement2.executeUpdate ();

                    if (V_update > 0) {

                        Alert alert = new Alert (Alert.AlertType.ERROR);
                        alert.setTitle ("Error");
                        alert.setHeaderText ("Error & Try Again");
                        alert.setContentText ("Error & Try Again database connection problem");
                        alert.showAndWait ();

                    }else {

                        connection.rollback ();
                        connection.setAutoCommit (true);
                        return true;
                    }

                } else {

                    return false;
                }

            }

        }

        connection.commit ();
        connection.setAutoCommit (true);
        return false;



    }



    public static void print(PrintDto printDto) throws IOException {
        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();

            // Create a new page
            PDPage page = new PDPage();
            document.addPage(page);

            // Prepare content stream
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Starting Y position
            float startY = 700;

            // Line spacing
            float lineSpacing = 20;

            // Write "Date"
            contentStream.beginText();
            contentStream.newLineAtOffset(100, startY);
            contentStream.showText("Date: " +printDto.getDate ());
            contentStream.endText();

            // Write "Production Name"
            contentStream.beginText();
            contentStream.newLineAtOffset(100, startY - 2 *  lineSpacing);
            contentStream.showText("Vehicle ID: " + printDto.getId());// Move down

            contentStream.endText();


            contentStream.beginText();
            contentStream.newLineAtOffset(100, startY - 4 * lineSpacing);
            contentStream.showText("Vehicle Model: " + printDto.getModel ());// Move down

            contentStream.endText();

            // Write "Production ID"
            contentStream.beginText();
            contentStream.newLineAtOffset(100, startY - 6 * lineSpacing); // Move down
            contentStream.showText("Vehicle  Brand: " + printDto.getBrand ());
            contentStream.endText();

            // Write "Unit Price"
            contentStream.beginText();
            contentStream.newLineAtOffset(100, startY - 8 * lineSpacing); // Move down
            contentStream.showText("Unit Price: " + printDto.getPrice ());
            contentStream.endText();

            // Write "Full Price"
            contentStream.beginText();
            contentStream.newLineAtOffset(100, startY - 10 * lineSpacing); // Move down
            contentStream.showText("Full Price: " + printDto.getTotalPrice());
            contentStream.endText();

            // Write "Quantity"
            contentStream.beginText();
            contentStream.newLineAtOffset(100, startY - 12 * lineSpacing); // Move down
            contentStream.showText("Qty: " + printDto.getQty());
            contentStream.endText();

            // Write "Total"
            contentStream.beginText();
            contentStream.newLineAtOffset(100, startY - 14 * lineSpacing); // Move down
            contentStream.showText("Total: " + printDto.getPrice());
            contentStream.endText();

            // Close the content stream
            contentStream.close();

            // Save the document to a file
            String pdfPath = "generatedBill.pdf";
            document.save(pdfPath);
            document.close();

            System.out.println("PDF generated successfully!");

            // Open the generated PDF if supported
            File pdfFile = new File(pdfPath);
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(pdfFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }



    }


