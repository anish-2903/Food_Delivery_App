package main.java.com.swift.app;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class OrderingScreen extends JFrame {
    private static final String MENU_ITEMS_FILE = "menu_items.txt";
    private static final String ORDER_FILE = "orders.txt";

    private MenuLinkedList menuList;

    public OrderingScreen() {
        setTitle("Ordering Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window

        // Load menu items from file into MenuLinkedList
        menuList = loadMenuItems();

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Display menu items with serial no., name, and price
        JPanel menuPanel = new JPanel(new GridLayout(menuList.size(), 3, 10, 10));
        if (menuList != null) {
            MenuItem current = menuList.getHead();
            int serialNo = 1;
            while (current != null) {
                JLabel serialLabel = new JLabel("Serial No.: " + serialNo);
                JLabel nameLabel = new JLabel("Name: " + current.name);
                JLabel priceLabel = new JLabel("Price: " + current.price + "Rs.");
                menuPanel.add(serialLabel);
                menuPanel.add(nameLabel);
                menuPanel.add(priceLabel);
                current = current.next;
                serialNo++;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error loading menu items.");
            dispose(); // Close the ordering screen on error
        }

        // Orders panel
        JPanel ordersPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        ordersPanel.setBorder(BorderFactory.createTitledBorder("Orders"));

        // Order selection components
        JPanel orderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField serialNoField = new JTextField(10);
        JTextField quantityField = new JTextField(10);
        JButton addButton = new JButton("Add");
        orderPanel.add(new JLabel("Enter Serial No.: "));
        orderPanel.add(serialNoField);
        orderPanel.add(new JLabel("Enter Quantity: "));
        orderPanel.add(quantityField);
        orderPanel.add(addButton);

        // Add action listener to Add button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get serial number and quantity entered by the user
                int serialNo = Integer.parseInt(serialNoField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                // Find the menu item corresponding to the selected serial number
                MenuItem menuItem = menuList.findMenuItemBySerialNo(serialNo);
                if (menuItem != null) {
                    // Calculate total price for the order
                    double totalPrice = quantity * menuItem.price;

                    // Write the order to the file
                    try (PrintWriter writer = new PrintWriter(new FileWriter(ORDER_FILE, true))) {
                        writer.println("Name: " + menuItem.name + ", Quantity: " + quantity + ", Total Price: " + totalPrice);
                        JOptionPane.showMessageDialog(OrderingScreen.this, "Order added successfully.");
                        // Update orders panel
                        JLabel orderLabel = new JLabel("Name: " + menuItem.name + ", Quantity: " + quantity + ", Total Price: " + totalPrice);
                        ordersPanel.add(orderLabel);
                        validate();
                        repaint();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(OrderingScreen.this, "Error adding order.");
                    }
                } else {
                    JOptionPane.showMessageDialog(OrderingScreen.this, "Invalid serial number.");
                }
            }
        });


        // Payment button
        JButton paymentButton = new JButton("Proceed to Payment");
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close ordering screen
                new PaymentGatewayScreen().setVisible(true); // Open payment gateway screen
            }
        });

        // Add components to panel
        panel.add(menuPanel, BorderLayout.CENTER);
        panel.add(orderPanel, BorderLayout.NORTH);
        panel.add(ordersPanel, BorderLayout.WEST);
        panel.add(paymentButton, BorderLayout.SOUTH);

        // Add panel to the frame
        add(panel);
    }

    // Method to load menu items from file into MenuLinkedList
    private MenuLinkedList loadMenuItems() {
        MenuLinkedList menuList = new MenuLinkedList();
        try (Scanner scanner = new Scanner(new File(MENU_ITEMS_FILE))) {
            while (scanner.hasNextLine()) {
                int serialId = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                String name = scanner.nextLine().split(": ")[1];
                double price = Double.parseDouble(scanner.nextLine().split(": ")[1]);

                menuList.insert(serialId, name, price);

                // Skip the empty line between menu items
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null; // Return null on error
        }
        return menuList;
    }

//    public static void main(String[] args) {
//        // Create and show the ordering screen
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new OrderingScreen().setVisible(true);
//            }
//        });
//    }
}