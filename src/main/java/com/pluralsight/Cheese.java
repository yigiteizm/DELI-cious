package com.pluralsight;

public class Cheese extends Topping {
    public Cheese(String name) {
        super(name);
    }

    @Override
    public double calculatePrice(int size) {
        if (size == 4) return 0.75;
        else if (size == 8) return 1.50;
        else return 2.25;

    }
}