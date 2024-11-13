package com.pluralsight;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Order currentOrder;
    private OrderFileManager fileManager;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.fileManager = new OrderFileManager();
    }

    public void displayMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        createNewOrder();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private void createNewOrder() {
        currentOrder = new Order();
        boolean ordering = true;
        while (ordering) {
            System.out.println("\n--- Order Menu ---");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:

                      
                    case 2:


                    case 3:


                    case 4:

                        ordering = false;
                        break;
                    case 0:
                        System.out.println("Order canceled.");
                        ordering = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}