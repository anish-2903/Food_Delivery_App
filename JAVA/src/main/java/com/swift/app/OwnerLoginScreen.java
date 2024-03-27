package main.java.com.swift.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class OwnerLoginScreen extends JFrame {
//    private static final String OWNER_DATA_FILE = "owner_data.txt";

    public OwnerLoginScreen() {
        setTitle("Owner Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose when closed
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns

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

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open MainScreen
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
                // Dispose of OwnerLoginScreen
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

                // Handle the case when no input is given
                if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty fields! Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    OwnerAuthentication authentication = new OwnerAuthentication();
                    if (authentication.authenticate(username, password, email, phone)) {
                        // Open AddMenuItemScreen after successful login
                        AddMenuItemScreen addMenuItemScreen = new AddMenuItemScreen();
                        addMenuItemScreen.setVisible(true);

                        // Dispose of OwnerLoginScreen
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
//        public static void main(String[] args) {
//        // Create and show the owner login screen
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new OwnerLoginScreen().setVisible(true);
//            }
//        });
//    }
}

class OwnerAuthentication {
    private static final String OWNER_DATA_FILE = "owner_data.txt";

    public boolean authenticate(String username, String password, String email, String phone) {
        File file = new File(OWNER_DATA_FILE);

        if (!file.exists()) {
            // If the file does not exist, save the owner data and return true
            saveOwnerData(username, password, email, phone);
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
            JOptionPane.showMessageDialog(null, "Error reading owner data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Username not found
    }

    // Method to save owner details to file
    private void saveOwnerData(String username, String password, String email, String phone) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(OWNER_DATA_FILE))) {
            writer.println("Username: " + Encryption.encrypt(username));
            writer.println("Password: " + Encryption.encrypt(password));
            writer.println("Email: " + Encryption.encrypt(email));
            writer.println("Phone Number: " + phone);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
