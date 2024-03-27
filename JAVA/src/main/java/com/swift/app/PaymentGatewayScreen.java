package main.java.com.swift.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentGatewayScreen extends JFrame {
    public PaymentGatewayScreen() {
        setTitle("Payment Gateway");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column

        // Online payment options
        JButton onlinePaymentButton = new JButton("Online Payment");
        onlinePaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openOnlinePaymentOptions();
            }
        });
        panel.add(onlinePaymentButton);

        // Cash on delivery option
        JButton codButton = new JButton("Cash on Delivery");
        codButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(PaymentGatewayScreen.this, "Thank you! You will get your delicious food just after 30-40 minutes.");
                openInvoice();
                dispose();
            }
        });
        panel.add(codButton);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close payment gateway screen
                // Open Main Screen
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
            }
        });
        panel.add(cancelButton);

        // Add panel to the frame
        add(panel);
    }

    private void openOnlinePaymentOptions() {
        String[] options = { "PAYTM", "PHONEPAY", "GPAY", "CARD PAY" };
        String input = (String) JOptionPane.showInputDialog(null, "Choose an online payment method:", "Online Payment",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (input != null) {
            JOptionPane.showMessageDialog(this, "Thank you! You will get your delicious food just after 30-40 minutes.");
            openInvoice();
            dispose();
        }
    }

    private void openInvoice() {
        int choice = JOptionPane.showConfirmDialog(this, "Would you like to generate your invoice?", "Generate Invoice",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // Generate and display the invoice
            InvoiceScreen invoiceScreen = new InvoiceScreen();
            invoiceScreen.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Thank you! Please come again...");
        }
    }

//    public static void main(String[] args) {
//        // Create and show the payment gateway screen
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new PaymentGatewayScreen().setVisible(true);
//            }
//        });
//    }
}