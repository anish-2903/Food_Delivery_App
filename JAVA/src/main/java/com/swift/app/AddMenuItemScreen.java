package main.java.com.swift.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddMenuItemScreen extends JFrame {
    private static final String MENU_FILE = "menu_items.txt";
    private int serialId = 1; // Initial serial ID for menu items

    public AddMenuItemScreen() {
        setTitle("Add Menu Items");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose when closed
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the window

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns

        // Menu item name label and text field
        JLabel nameLabel = new JLabel("Menu Item Name:");
        JTextField nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        // Price label and text field
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();
        panel.add(priceLabel);
        panel.add(priceField);

        // Add button
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve entered details
                String name = nameField.getText();
                String price = priceField.getText();

                // Add menu item to file
                addMenuItemToFile(name, price);

                // Increment serial ID
                serialId++;

                // Clear text fields
                nameField.setText("");
                priceField.setText("");

                // Ask for adding more items
                int option = JOptionPane.showConfirmDialog(AddMenuItemScreen.this, "Do you want to add more items?", "Add More Items", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION) {
                    // If user clicks "No", dispose of AddMenuItemScreen
                    dispose();
                    // Save menu items to file
                    saveMenuItems();
                    // Open MainScreen
                    MainScreen mainScreen = new MainScreen();
                    mainScreen.setVisible(true);
                }
            }
        });
        panel.add(addButton);

        // Done button
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Save menu items to file
                saveMenuItems();
                // Dispose of AddMenuItemScreen
                dispose();
                // Open MainScreen
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
            }
        });
        panel.add(doneButton);

        // Add panel to the frame
        add(panel);
    }

    // Method to add menu item to file
    private void addMenuItemToFile(String name, String price) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MENU_FILE, true))) {
            writer.println("Serial ID: " + serialId);
            writer.println("Name: " + name);
            writer.println("Price: " + price);
            writer.println(); // Empty line to separate menu items
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save menu items to file
    private void saveMenuItems() {
        // Nothing needed here as menu items are already saved while adding
    }

//    public static void main(String[] args) {
//        // Create and show the add menu item screen
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new AddMenuItemScreen().setVisible(true);
//            }
//        });
//    }
}