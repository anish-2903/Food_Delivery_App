package main.java.com.swift.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelBasedOrderingScreen extends JFrame {
    public HotelBasedOrderingScreen() {
        setTitle("Hotel-based Ordering");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10)); // 7 rows, 1 column

        // Hotel options
        JButton hotel1Button = new JButton("Hotel 1");
        hotel1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openOrderingScreen("Hotel 1");
            }
        });
        panel.add(hotel1Button);

        JButton hotel2Button = new JButton("Hotel 2");
        hotel2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openOrderingScreen("Hotel 2");
            }
        });
        panel.add(hotel2Button);

        JButton hotel3Button = new JButton("Hotel 3");
        hotel3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openOrderingScreen("Hotel 3");
            }
        });
        panel.add(hotel3Button);

        JButton hotel4Button = new JButton("Hotel 4");
        hotel4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openOrderingScreen("Hotel 4");
            }
        });
        panel.add(hotel4Button);

        JButton hotel5Button = new JButton("Hotel 5");
        hotel5Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openOrderingScreen("Hotel 5");
            }
        });
        panel.add(hotel5Button);

        JButton hotel6Button = new JButton("Hotel 6");
        hotel6Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openOrderingScreen("Hotel 6");
            }
        });
        panel.add(hotel6Button);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Dispose of HotelBasedOrderingScreen
                dispose();
                // Open OrderOptionsScreen
                OrderOptionsScreen orderOptionsScreen = new OrderOptionsScreen();
                orderOptionsScreen.setVisible(true);
            }
        });
        panel.add(backButton);

        // Add panel to the frame
        add(panel);
    }

    private void openOrderingScreen(String hotelName) {
        // Dispose of HotelBasedOrderingScreen
        dispose();
        // Open OrderingScreen for the selected hotel
        OrderingScreen orderingScreen = new OrderingScreen();
        orderingScreen.setVisible(true);
    }

//    public static void main(String[] args) {
//        // Create and show the hotel-based ordering screen
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new HotelBasedOrderingScreen().setVisible(true);
//            }
//        });
//    }
}