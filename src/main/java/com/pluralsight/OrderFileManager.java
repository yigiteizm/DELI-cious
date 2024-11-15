package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderFileManager {
    public void saveReceipt(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String fileName = "receipts/" + LocalDateTime.now().format(formatter) + ".txt";

        try {
            Files.createDirectories(Paths.get("receipts"));
            FileWriter writer = new FileWriter(fileName);
            writer.write(order.toString());
            writer.close();
            System.out.println("Order saved to " + fileName);
        } catch (Exception e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }
}