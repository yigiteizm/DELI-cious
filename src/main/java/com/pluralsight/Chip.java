package com.pluralsight;

public class Chip {
    private String type;

    public Chip(String type) {
        this.type = type;
    }

    public double calculatePrice() {
        return 1.50;
    }

    @Override
    public String toString() {
        return String.format("Chip Type: %s - $%.2f", type, calculatePrice());
    }
}