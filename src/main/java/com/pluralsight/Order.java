package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chip> chips;

    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChip(Chip chip) {
        chips.add(chip);
    }

    public double calculateTotal() {
        double total = 0;
        for (Sandwich sandwich : sandwiches) total += sandwich.calculatePrice();
        for (Drink drink : drinks) total += drink.calculatePrice();
        for (Chip chip : chips) total += chip.calculatePrice();
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sandwich sandwich : sandwiches) sb.append(sandwich.toString()).append("\n");
        for (Drink drink : drinks) sb.append(drink.toString()).append("\n");
        for (Chip chip : chips) sb.append(chip.toString()).append("\n");
        sb.append("Total: $").append(calculateTotal());
        return sb.toString();
    }
}