package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private boolean isToasted;
    private String size;
    private String breadType;
    private List<Topping> toppings;

    public Sandwich(String size, String breadType) {
        this.size = size;
        this.breadType = breadType;
        this.toppings = new ArrayList<>();
    }

    public void setToasted(boolean toasted) {
        this.isToasted = toasted;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void addSauce(String sauce) {
        this.toppings.add(new Sauce(sauce));
    }

    private double getBreadPrice() {
        switch (size) {
            case "4":
                return 5.50;
            case "8":
                return 7.00;
            case "12":
                return 8.50;
            default:
                throw new IllegalArgumentException("Invalid sandwich size: " + size);
        }
    }

    public double calculatePrice() {
        double price = getBreadPrice();
        for (Topping topping : toppings) {
            price += topping.calculatePrice(Integer.parseInt(size));
        }
        return price;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Sandwich  Size: " + size + ", Bread: " + breadType +
                " - Bread Price: $" + String.format("%.2f", getBreadPrice()) + "\n");

        StringBuilder toppingsString = new StringBuilder();
        for (Topping topping : toppings) {
            if (toppingsString.length() > 0) {
                toppingsString.append(", ");
            }
            toppingsString.append(topping.name).append(String.format(" ($%.2f)", topping.calculatePrice(Integer.parseInt(size))));
        }
        if (toppingsString.length() > 0) {
            result.append("Toppings: ").append(toppingsString.toString()).append("\n");
        }


        result.append("Toasted: ").append(isToasted ? "Yes" : "No").append("\n");

        return result.toString().trim();
    }
}