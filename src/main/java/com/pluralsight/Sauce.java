package com.pluralsight;

public class Sauce extends Topping {
    public Sauce(String name) {
        super(name);
    }

    @Override
    public double calculatePrice(int size) {
        return 0.0;

    }
}