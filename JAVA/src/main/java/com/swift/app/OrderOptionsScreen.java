package main.java.com.swift.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OrderOptionsScreen extends JFrame {
    public OrderOptionsScreen() {
        setTitle("Order Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the window

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column

        // Hotel-based ordering button
        JButton hotelOrderButton = new JButton("Hotel-based Ordering");
        hotelOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement hotel-based ordering functionality here
                HotelBasedOrderingScreen hotelBasedOrderingScreen = new HotelBasedOrderingScreen();
                hotelBasedOrderingScreen.setVisible(true);

                dispose();
            }
        });
        panel.add(hotelOrderButton);

        // Food-based ordering button
        JButton foodOrderButton = new JButton("Food-based Ordering");
        foodOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement food-based ordering functionality here
                // Open OrderOptionsScreen after successful login
                OrderingScreen OrderingScreen = new OrderingScreen();
                OrderingScreen.setVisible(true);

                dispose();
            }
        });
        panel.add(foodOrderButton);

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Dispose of OrderOptionsScreen
                dispose();
                // Open MainScreen
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
            }
        });
        panel.add(exitButton);

        // Add panel to the frame
        add(panel);
    }

//    public static void main(String[] args) {
//        // Create and show the order options screen
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new OrderOptionsScreen().setVisible(true);
//            }
//        });
//    }
}