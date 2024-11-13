package com.pluralsight;

public class Drink {
    private String size;
    private String flavors;

    public Drink(String size, String flavors) {
        this.size = size;
        this.flavors = flavors;
    }

    public double calculatePrice() {
        if ("small".equals(size)) {
            return 2.00;
        } else if ("medium".equals(size)) {
            return 2.50;
        } else {
            return 3.00;
        }
    }

    @Override
    public String toString() {
        return String.format("Drink - Size: %s, Flavors: %s - $%.2f", size, flavors, calculatePrice());
    }
}