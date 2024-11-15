package com.pluralsight;

public class RegularTopping extends Topping {
    public RegularTopping(String name) {
        super(name);
    }

    @Override
    public double calculatePrice(int size) {
        return 0.0;
    }
}