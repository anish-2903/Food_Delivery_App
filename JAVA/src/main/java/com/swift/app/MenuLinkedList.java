package main.java.com.swift.app;

//import java.io.*;
//import java.util.*;

class MenuItem {
    int serialId;
    String name;
    double price;
    MenuItem next;

    public MenuItem(int serialId, String name, double price) {
        this.serialId = serialId;
        this.name = name;
        this.price = price;
        this.next = null;
    }
}

public class MenuLinkedList {
    private MenuItem head;

    public MenuLinkedList() {
        head = null;
    }

    public void insert(int serialId, String name, double price) {
        MenuItem newItem = new MenuItem(serialId, name, price);
        if (head == null) {
            head = newItem;
        } else {
            MenuItem current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newItem;
        }
    }

    public void display() {
        MenuItem current = head;
        while (current != null) {
            System.out.println("Serial ID: " + current.serialId);
            System.out.println("Name: " + current.name);
            System.out.println("Price: " + current.price);
            System.out.println();
            current = current.next;
        }
    }

    // Method to get the head of the linked list
    public MenuItem getHead() {
        return head;
    }

    // Method to get the size of the linked list
    public int size() {
        int count = 0;
        MenuItem current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Method to find a menu item by serial number
    public MenuItem findMenuItemBySerialNo(int serialNo) {
        MenuItem current = head;
        while (current != null) {
            if (current.serialId == serialNo) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}


//    public static void main(String[] args) {
//        MenuLinkedList menuList = new MenuLinkedList();
//
//        try (Scanner scanner = new Scanner(new File("menu_items.txt"))) {
//            while (scanner.hasNextLine()) {
//                int serialId = Integer.parseInt(scanner.nextLine().split(": ")[1]);
//                String name = scanner.nextLine().split(": ")[1];
//                double price = Double.parseDouble(scanner.nextLine().split(": ")[1]);
//
//                menuList.insert(serialId, name, price);
//
//                // Skip the empty line between menu items
//                scanner.nextLine();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // Display the menu items
//        menuList.display();
//    }