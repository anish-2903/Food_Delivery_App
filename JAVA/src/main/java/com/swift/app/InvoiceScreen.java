package main.java.com.swift.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class InvoiceScreen extends JFrame {
    private static final String CUSTOMER_DATA_FILE = "customer_data.txt";
    private static final String ORDER_FILE = "orders.txt";

    public InvoiceScreen() {
        setTitle("Invoice");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        // Load customer information from file
        String[] customerInfo = loadCustomerInfo();

        // Load orders from file and calculate total bill with GST
        double total = calculateTotalBill();

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10)); // 7 rows, 1 column

        // Customer information
        JLabel nameLabel = new JLabel("Name: " + customerInfo[0]);
        JLabel addressLabel = new JLabel("Email: " + customerInfo[1]);
        JLabel phoneLabel = new JLabel("Phone: " + customerInfo[2]);
        JLabel emailLabel = new JLabel("Address: " + customerInfo[3]);
        panel.add(nameLabel);
        panel.add(addressLabel);
        panel.add(phoneLabel);
        panel.add(emailLabel);

        // Orders
        JTextArea orderArea = new JTextArea();
        orderArea.setEditable(false);
        orderArea.setLineWrap(true);
        orderArea.setWrapStyleWord(true);
        orderArea.setText(loadOrders());
        panel.add(new JScrollPane(orderArea));

        // Total Bill
        JLabel totalLabel = new JLabel("Total Bill (including 18% GST): " + total);
        panel.add(totalLabel);

        // Thank you message
        JLabel thankYouLabel = new JLabel("Thank you! Please come again...");
        panel.add(thankYouLabel);

        // Add panel to the frame
        add(panel);
    }

    private String[] loadCustomerInfo() {
        String[] customerInfo = new String[4];
        int index = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":"); // Split line by colon
                if (parts[0].equalsIgnoreCase("password")) {
                    continue; // Skip password line
                }
                customerInfo[index++] = parts[1].trim(); // Read information after colon and trim whitespace
                if (index >= 4) {
                    break; // Break loop if all information is read
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerInfo;
    }



    private String loadOrders() {
        StringBuilder orders = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                orders.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders.toString();
    }

    private double calculateTotalBill() {
        double total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // Split line by comma and space
                String totalString = (parts[2].split(": "))[1]; // Extract total price string
                double totalPrice = Double.parseDouble(totalString); // Parse total price
                total += totalPrice; // Add total price to total bill
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Add GST (18%)
        double gst = total * 0.18;
        total += gst;
        return total;
    }



    public static void main(String[] args) {
        // Create and show the invoice screen
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InvoiceScreen().setVisible(true);
            }
        });
    }
}