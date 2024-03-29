package main.java.com.swift.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CustomerLoginScreen extends JFrame {
    private static final String CUSTOMER_DATA_FILE = "customer_data.txt";

    public CustomerLoginScreen() {
        setTitle("Customer Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose when closed
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 rows, 2 columns

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        panel.add(usernameLabel);
        panel.add(usernameField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Email label and text field
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        panel.add(emailLabel);
        panel.add(emailField);

        // Phone number label and text field
        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        panel.add(phoneLabel);
        panel.add(phoneField);

        // Address label and text field
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        panel.add(addressLabel);
        panel.add(addressField);


        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open MainScreen
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
                // Dispose of CustomerLoginScreen
                dispose();
            }
        });
        panel.add(backButton);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve entered details
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();

                // Handle the no input case
                if (username.isEmpty() || password.isEmpty() || email.isEmpty()
                        || phone.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty fields! Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Authentication authentication = new Authentication();
                    if (authentication.authenticate(username, password, email, phone, address)) {
                        // Open OrderOptionsScreen after successful login
                        OrderOptionsScreen orderOptionsScreen = new OrderOptionsScreen();
                        orderOptionsScreen.setVisible(true);

                        // Dispose of CustomerLoginScreen
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong username/password!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panel.add(loginButton);

        // Add panel to the frame
        add(panel);
    }
//    public static void main(String[] args) {
//        // Create and show the customer login screen
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new CustomerLoginScreen().setVisible(true);
//            }
//        });
//    }
}

class Authentication {
    private static final String CUSTOMER_DATA_FILE = "customer_data.txt";

    public boolean authenticate(String username, String password, String email, String phone, String address) {
        File file = new File(CUSTOMER_DATA_FILE);

        if (!file.exists()) {
            // If the file does not exist, save the user data and return true
            saveCustomerData(username, password, email, phone, address);
            return true;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            username = Encryption.encrypt(username);
            password = Encryption.encrypt(password);

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: " + username)) {
                    line = reader.readLine(); // Read next line (password)
                    if (line.startsWith("Password: " + password)) {
                        return true; // Username and password match
                    } else {
                        return false; // Password does not match
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading user data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Username not found
    }

    // Method to save customer details to file
    private void saveCustomerData(String username, String password, String email, String phone, String address) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMER_DATA_FILE))) {
            writer.println("Username: " + Encryption.encrypt(username));
            writer.println("Password: " + Encryption.encrypt(password));
            writer.println("Email: " + Encryption.encrypt(email));
            writer.println("Phone Number: " + phone);
            writer.println("Address: " + Encryption.encrypt(address));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}