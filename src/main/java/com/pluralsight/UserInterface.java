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
                        customizeSandwich();
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
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

    private void customizeSandwich() {
        System.out.print("Enter sandwich size (4, 8, or 12): ");
        String size = scanner.nextLine();
        System.out.print("Enter bread type (white, wheat, rye, wrap): ");
        String breadType = scanner.nextLine();
        Sandwich newSandwich = new Sandwich(size, breadType);

        boolean addingToppings = true;
        while (addingToppings) {
            System.out.println("\n--- Toppings ---");
            System.out.println("1) Add Meat");
            System.out.println("2) Add Cheese");
            System.out.println("3) Add Regular Topping");
            System.out.println("4) Add Sauce");
            System.out.println("5) Finish Sandwich");
            try {
                int toppingChoice = Integer.parseInt(scanner.nextLine());
                switch (toppingChoice) {
                    case 1:
                        System.out.print("Enter meat type: ");
                        newSandwich.addTopping(new Meat(scanner.nextLine()));
                        break;
                    case 2:
                        System.out.print("Enter cheese type: ");
                        newSandwich.addTopping(new Cheese(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.print("Enter regular topping: ");
                        newSandwich.addTopping(new RegularTopping(scanner.nextLine()));
                        break;
                    case 4:
                        System.out.print("Enter sauce (or 'done' to finish): ");
                        String sauce = scanner.nextLine();
                        if (!sauce.equalsIgnoreCase("done")) {
                            newSandwich.addSauce(sauce);
                        }
                        break;
                    case 5:
                        addingToppings = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number for the topping choice.");
            }
        }

        System.out.print("Do you want the sandwich toasted? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            newSandwich.setToasted(true);
        }
        currentOrder.addSandwich(newSandwich);
        System.out.println("Sandwich added to order.");
    }
}