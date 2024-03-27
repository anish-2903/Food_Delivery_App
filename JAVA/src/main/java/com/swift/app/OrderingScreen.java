package main.java.com.swift.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class OrderingScreen extends JFrame {
    private static final String MENU_FILE = "menu_items.txt";
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
                String serialNoText = serialNoField.getText().trim();
                String quantityText = quantityField.getText().trim();

                // Check if any field is empty
                if (serialNoText.isEmpty() || quantityText.isEmpty()) {
                    JOptionPane.showMessageDialog(OrderingScreen.this, "Please enter both serial number and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method if any field is empty
                }

                // Parse the input to integers
                int serialNo = 0, quantity = 0;
                try {
                    serialNo = Integer.parseInt(serialNoText);
                    quantity = Integer.parseInt(quantityText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(OrderingScreen.this, "Invalid input for serial number or quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method if input cannot be parsed to integers
                }

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
// Method to load menu items from file into MenuLinkedList
    private MenuLinkedList loadMenuItems() {
        MenuLinkedList menuList = new MenuLinkedList();
        File menuFile = new File(MENU_FILE);
        if (menuFile.exists()) {
            try {
                Scanner scanner = new Scanner(menuFile);
                while (scanner.hasNextLine()) {
                    int serialId = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                    String name = scanner.nextLine().split(": ")[1];
                    double price = Double.parseDouble(scanner.nextLine().split(": ")[1]);

                    menuList.insert(serialId, name, price);

                    // Skip the empty line between menu items
                    scanner.nextLine();
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null; // Return null on error
            }
        } else {
            // If menu file doesn't exist, create default menu items
            try {
                createDefaultMenuItemsFile();
                return loadMenuItems(); // Load menu items recursively after creating the file
            } catch (IOException e) {
                e.printStackTrace();
                return null; // Return null on error
            }
        }
        return menuList;
    }

    // Method to create a default menu items file with some entries
    private void createDefaultMenuItemsFile() throws IOException {
        File menuFile = new File(MENU_FILE);

        if (!menuFile.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(MENU_ITEMS_FILE))) {
                writer.println("Serial ID: 1");
                writer.println("Name: Burger");
                writer.println("Price: 40.0");
                writer.println(); //

                writer.println("Serial ID: 2");
                writer.println("Name: Cake");
                writer.println("Price: 400.0");
                writer.println(); //

                writer.println("Serial ID: 3");
                writer.println("Name: Fried Rice");
                writer.println("Price: 80.0");
                writer.println(); //
            }
        }
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
