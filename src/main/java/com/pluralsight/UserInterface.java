package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

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
            System.out.println("\n--- DELI-cious Sandwich Shop ---");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Please select an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        createNewOrder();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Thank you for using DELI-cious Sandwich Shop. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void createNewOrder() {
        currentOrder = new Order();
        boolean ordering = true;
        while (ordering) {
            System.out.println("\n--- DELI-cious Order Menu ---");
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
                        addDrink();
                        break;
                    case 3:
                        addChips();
                        break;
                    case 4:
                        checkout();
                        ordering = false;
                        break;
                    case 0:
                        System.out.println("Order canceled.");
                        ordering = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private void customizeSandwich() {
        System.out.print("Enter sandwich size (4, 8, or 12): ");
        String size = scanner.nextLine();
        System.out.print("Enter bread type (white, wheat, rye, wrap): ");
        String breadType = scanner.nextLine();
        Sandwich newSandwich = new Sandwich(size, breadType.substring(0, 1).toUpperCase() + breadType.substring(1).toLowerCase());

        boolean addingToppings = true;
        while (addingToppings) {
            System.out.println("\n--- DELI-cious Topping Options ---");
            System.out.println("1) Add Meat");
            System.out.println("2) Add Cheese");
            System.out.println("3) Add Regular Topping");
            System.out.println("4) Add Sauce");
            System.out.println("5) Finish Sandwich");
            try {
                int toppingChoice = Integer.parseInt(scanner.nextLine());
                switch (toppingChoice) {
                    case 1:
                        addToppings(newSandwich, "Meat", Arrays.asList("Steak", "Ham", "Salami", "Roast beef", "Chicken", "Bacon"));
                        break;
                    case 2:
                        addToppings(newSandwich, "Cheese", Arrays.asList("American", "Provolone", "Cheddar", "Swiss"));
                        break;
                    case 3:
                        addToppings(newSandwich, "Regular Topping", Arrays.asList("Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapenos", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"));
                        break;
                    case 4:
                        addSauces(newSandwich);
                        break;
                    case 5:
                        addingToppings = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
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

    private void addToppings(Sandwich sandwich, String toppingType, List<String> options) {
        System.out.println("DELI-cious " + toppingType + " Enter numbers separated by commas to add toppings, then press Enter:");
        System.out.println("You can make multiple choices by using commas, for example (1,2,3)");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ") " + options.get(i));
        }

        System.out.print("Enter your choices: ");
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                String[] selections = input.split("[,\\s]+");
                for (String selection : selections) {
                    if (selection.isEmpty()) continue;
                    int index = Integer.parseInt(selection) - 1;
                    if (index >= 0 && index < options.size()) {
                        String selectedOption = options.get(index);
                        switch (toppingType) {
                            case "Meat":
                                sandwich.addTopping(new Meat(selectedOption));
                                break;
                            case "Cheese":
                                sandwich.addTopping(new Cheese(selectedOption));
                                break;
                            case "Regular Topping":
                                sandwich.addTopping(new RegularTopping(selectedOption));
                                break;
                        }
                    } else {
                        System.out.println("Invalid selection: " + (index + 1) + ". Please try again.");

                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numbers.");

            }
        }

        System.out.print("Are you sure you want to finish selecting " + toppingType.toLowerCase() + "? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            return;
        } else {

        }
    }

    private void addSauces(Sandwich sandwich) {
        List<String> sauces = Arrays.asList("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand islands", "Vinaigrette");
        System.out.println("DELI-cious Sauce Options Enter numbers separated by commas to add sauces, then press Enter:");
        System.out.println("You can make multiple choices by using commas, for example (1,2,3)");
        for (int i = 0; i < sauces.size(); i++) {
            System.out.println((i + 1) + ") " + sauces.get(i));
        }

        System.out.print("Enter your choices: ");
        String input = scanner.nextLine().trim();
        List<String> selectedSauces = new ArrayList<>();

        if (!input.isEmpty()) {
            String[] selections = input.split("[,\\s]+");
            for (String selection : selections) {
                if (selection.isEmpty()) continue;
                try {
                    int index = Integer.parseInt(selection) - 1;
                    if (index >= 0 && index < sauces.size()) {
                        selectedSauces.add(sauces.get(index));
                    } else {
                        System.out.println("Invalid sauce selection: " + (index + 1) + ". Skipping.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter valid numbers. Skipping invalid input.");
                }
            }

            System.out.print("Are you sure you want to finish selecting sauces? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {

                for (String sauce : selectedSauces) {
                    sandwich.addSauce(sauce);
                }
            } else {
                System.out.println("Sauce selection canceled. No sauces added.");
            }
        }
    }

    private void addDrink() {
        List<String> sizes = Arrays.asList("Small", "Medium", "Large");
        List<String> flavors = Arrays.asList("Coke", "Lemonade", "Iced Tea", "Orange");

        System.out.println("--- DELI-cious Drink Menu ---");
        System.out.println("Drink Sizes:");
        for (int i = 0; i < sizes.size(); i++) {
            System.out.println((i + 1) + ") " + sizes.get(i));
        }

        System.out.print("Please select a drink size by number: ");
        String sizeInput = scanner.nextLine();
        int sizeIndex = 0; // Default to Small

        try {
            sizeIndex = Integer.parseInt(sizeInput) - 1;
            if (sizeIndex < 0 || sizeIndex >= sizes.size()) {
                System.out.println("Invalid size, using Small.");
                sizeIndex = 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a number, using Small.");
        }

        String size = sizes.get(sizeIndex).toLowerCase();

        System.out.println("\nDrink Flavors:");
        for (int i = 0; i < flavors.size(); i++) {
            System.out.println((i + 1) + ") " + flavors.get(i));
        }
        System.out.println((flavors.size() + 1) + ") No Flavor");

        System.out.print("Please select a drink flavor by number: ");
        String flavorInput = scanner.nextLine();
        int flavorIndex = flavors.size(); // Default to last index + 1 for "No Flavor"

        try {
            flavorIndex = Integer.parseInt(flavorInput) - 1;
            if (flavorIndex < 0 || flavorIndex > flavors.size()) {
                System.out.println("Invalid flavor, using None.");
                flavorIndex = flavors.size();
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a number, using None.");
        }

        String flavor = flavorIndex < flavors.size() ? flavors.get(flavorIndex) : "";

        Drink drink = new Drink(size, flavor);
        currentOrder.addDrink(drink);
        System.out.println("Drink added to order.");
    }

    private void addChips() {
        List<String> chipOptions = Arrays.asList("Regular", "Barbecue", "Salt & Vinegar");
        System.out.println("\n--- DELI-cious Chips Options ---");
        for (int i = 0; i < chipOptions.size(); i++) {
            System.out.println((i + 1) + ") " + chipOptions.get(i) + " Chips");
        }
        System.out.println("4) No Chips");

        System.out.print("Please select a chip option (1-4): ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    currentOrder.addChip(new Chip("Regular Chips"));
                    System.out.println("Regular Chips added to your order.");
                    break;
                case 2:
                    currentOrder.addChip(new Chip("Barbecue Chips"));
                    System.out.println("Barbecue Chips added to your order.");
                    break;
                case 3:
                    currentOrder.addChip(new Chip("Salt & Vinegar Chips"));
                    System.out.println("Salt & Vinegar Chips added to your order.");
                    break;
                case 4:
                    System.out.println("No chips added.");
                    break;
                default:
                    System.out.println("Invalid choice. No chips added.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number next time. No chips added.");
        }
    }

        private void checkout() {
            System.out.println(currentOrder.toString());
            System.out.print("Confirm order? (y/n): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                try {
                    fileManager.saveReceipt(currentOrder);
                    System.out.println("Order confirmed and saved.");
                } catch (Exception e) {
                    System.out.println("An error occurred while saving the receipt: " + e.getMessage());
                }
            } else {
                System.out.println("Order canceled.");
            }
        }
}