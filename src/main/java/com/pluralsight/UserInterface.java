package com.pluralsight;

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

    private void addToppings(Sandwich sandwich, String toppingType, List<String> options) {
        System.out.println("DELI-cious " + toppingType + " Enter numbers separated by commas or (OK) to finish:");
        System.out.println("You can make multiple choose by using comma for example (1,2,3)");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ") " + options.get(i));
        }

        boolean finished = false;
        while (!finished) {
            System.out.print("Enter your choices or enter (OK) to finish: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("OK")) {
                System.out.print("Are you sure you want to finish selecting " + toppingType.toLowerCase() + "? (y/n): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    finished = true;
                    continue;
                }
            }
            try {
                String[] selections = input.split("[,\\s]+");
                for (String selection : selections) {
                    if (selection.isEmpty()) continue; // Boş seçimleri atla
                    int index = Integer.parseInt(selection.trim()) - 1;
                    if (index >= 0 && index < options.size()) {
                        String selectedOption = options.get(index);
                        if (toppingType.equals("Meat")) {
                            sandwich.addTopping(new Meat(selectedOption));
                        } else if (toppingType.equals("Cheese")) {
                            sandwich.addTopping(new Cheese(selectedOption));
                        } else if (toppingType.equals("Regular Topping")) {
                            sandwich.addTopping(new RegularTopping(selectedOption));
                        }
                    } else {
                        System.out.println("Invalid selection: " + (index + 1) + ". Please try again.");
                    }
                }
            } catch (NumberFormatException e) {
                if (!input.equalsIgnoreCase("OK")) { // Sadece "OK" girişi değilse hata mesajı ver.
                    System.out.println("Please enter valid numbers.");
                }
            }
        }
    }

    private void addSauces(Sandwich sandwich) {
        List<String> sauces = Arrays.asList("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand islands", "Vinaigrette");
        System.out.println("DELI-cious Sauce Options Enter numbers separated by commas or (OK) to finish:");
        System.out.println("You can make multiple choose by using comma for example (1,3,5)");
        for (int i = 0; i < sauces.size(); i++) {
            System.out.println((i + 1) + ") " + sauces.get(i));
        }

        boolean finished = false;
        while (!finished) {
            System.out.print("Enter your choices or enter (OK) to finish: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("OK")) {
                System.out.print("Are you sure you want to finish selecting sauces? (y/n): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    finished = true;
                    continue;
                }
            }
            try {
                String[] selections = input.split("[,\\s]+");
                for (String selection : selections) {
                    if (selection.isEmpty()) continue; // Boş seçimleri atla
                    int index = Integer.parseInt(selection.trim()) - 1;
                    if (index >= 0 && index < sauces.size()) {
                        sandwich.addSauce(sauces.get(index));
                    } else {
                        System.out.println("Invalid sauce selection: " + (index + 1) + ". Please try again.");
                    }
                }
            } catch (NumberFormatException e) {
                if (!input.equalsIgnoreCase("OK")) { // Sadece "OK" girişi değilse hata mesajı ver.
                    System.out.println("Please enter valid numbers.");
                }
            }
        }
    }

    private void addDrink() {
        System.out.print("Enter drink size (small, medium, large): ");
        String size = scanner.nextLine();
        if (!size.equalsIgnoreCase("small") && !size.equalsIgnoreCase("medium") && !size.equalsIgnoreCase("large")) {
            System.out.println("Invalid drink size. Defaulting to 'small'.");
            size = "small";
        }

        System.out.println("Drink Flavor Options:");
        for (String flavor : Arrays.asList("Coke", "Lemonade", "Iced Tea", "Orange", "None")) {
            System.out.println("- " + flavor);
        }

        System.out.print("Enter flavor (or (None) if you don't want a flavor): ");
        String flavor = scanner.nextLine();
        if (flavor.equalsIgnoreCase("none")) {
            flavor = "";
        } else {
            flavor = flavor.substring(0, 1).toUpperCase() + flavor.substring(1).toLowerCase();
        }

        Drink drink = new Drink(size, flavor);
        currentOrder.addDrink(drink);
        System.out.println("Drink added to order.");
    }

    private void addChips() {
        System.out.println("Would you like to add a DELI-cious Special Chip? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("yes") || choice.equals("y")) {
            currentOrder.addChip(new Chip("DELI-cious Special"));
            System.out.println("DELI-cious Special Chip added to your order.");
        } else {
            System.out.println("No special chip added.");
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