package main.java.com.swift.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen extends JFrame {
    public MainScreen() {
        setTitle("Food Delivery System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Create a panel for the welcome message
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        // Add space at the top
        welcomePanel.add(Box.createVerticalStrut(50));

        // Welcome message label
        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'><font color='#FF0000'><b>WELCOME TO THE FOOD DELIVERY SYSTEM</b></font></div></html>");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);

        // Add space in the middle
        welcomePanel.add(Box.createVerticalStrut(50));

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // Owner login button
        JButton ownerLoginButton = new JButton("Owner Login");
        ownerLoginButton.setFont(new Font("Arial", Font.BOLD, 16));
        ownerLoginButton.setForeground(Color.WHITE);
        ownerLoginButton.setBackground(new Color(59, 89, 182));
        ownerLoginButton.setFocusPainted(false);
        ownerLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open OwnerLoginScreen
                OwnerLoginScreen ownerLoginScreen = new OwnerLoginScreen();
                ownerLoginScreen.setVisible(true);
                // Dispose of MainScreen
                dispose();
            }
        });
        buttonPanel.add(ownerLoginButton);

        // Customer login button
        JButton customerLoginButton = new JButton("Customer Login");
        customerLoginButton.setFont(new Font("Arial", Font.BOLD, 16));
        customerLoginButton.setForeground(Color.WHITE);
        customerLoginButton.setBackground(new Color(59, 89, 182));
        customerLoginButton.setFocusPainted(false);
        customerLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open CustomerLoginScreen
                CustomerLoginScreen customerLoginScreen = new CustomerLoginScreen();
                customerLoginScreen.setVisible(true);
                // Dispose of MainScreen
                dispose();
            }
        });
        buttonPanel.add(customerLoginButton);

        // Add panels to the frame
        add(welcomePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

//    public static void main(String[] args) {
//        // Create and show the main screen
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new MainScreen().setVisible(true);
//            }
//        });
//    }
}