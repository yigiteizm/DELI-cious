package com.pluralsight;

public class Meat extends Topping {
    public Meat(String name) {
        super(name);
    }

    @Override
    public double calculatePrice(int size) {
        if (size == 4) return 1.00;
        else if (size == 8) return 2.00;
        else return 3.00;
    }
}