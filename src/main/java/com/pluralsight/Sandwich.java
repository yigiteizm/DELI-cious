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
        if (breadType.equals("white")) return size.equals("4") ? 5.50 : size.equals("8") ? 7.00 : 8.50;
        else if (breadType.equals("wheat")) return size.equals("4") ? 6.00 : size.equals("8") ? 7.50 : 9.00;
        else if (breadType.equals("rye")) return size.equals("4") ? 6.50 : size.equals("8") ? 8.00 : 9.50;
        else return size.equals("4") ? 7.00 : size.equals("8") ? 8.50 : 10.00; // wrap
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
       
        String result = "Sandwich - Size: " + size + ", Bread: " + breadType +
                " - Price: $" + String.format("%.2f", calculatePrice()) + "\n";

        for (Topping topping : toppings) {
            if (result.endsWith(", ")) {
                result = result + topping.toString();
            } else {
                result += topping.toString() + ", ";
            }
        }

        if (isToasted) {
            result += " - Toasted";
        }

        if (result.endsWith(", ")) {
            result = result.substring(0, result.length() - 2);
        }

        return result;
    }
}