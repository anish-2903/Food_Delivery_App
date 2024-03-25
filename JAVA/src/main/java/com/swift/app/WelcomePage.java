package main.java.com.swift.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Objects;
import javax.imageio.ImageIO;

public class WelcomePage extends JFrame {
    public WelcomePage() {
        setTitle("Welcome to Food Delivery App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE); // Set background color

        // Add image section for logo
        JLabel logoLabel = new JLabel();
        // Load image from classpath resource
        try {

            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("Logo.png")));

            img = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println("Error loading image: " + ex.getMessage());
        }

        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(logoLabel, BorderLayout.CENTER);

        // Add button
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        startButton.setBackground(new Color(59, 89, 182)); // Custom background color
        startButton.setForeground(Color.WHITE); // Text color
        startButton.setFocusPainted(false); // Remove focus border
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action to start the application
                // For now, just display a message
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
                dispose();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel to center the button
        buttonPanel.add(startButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panel to the frame
        add(panel);

    }

    public static void main(String[] args) {
        // Create and show the welcome screen
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WelcomePage().setVisible(true);
            }
        });
    }
}