package com.pluralsight;

abstract class Topping {
    protected String name;

    public Topping(String name) {
        this.name = name;
    }

    public abstract double calculatePrice(int size);

    @Override
    public String toString() {
        return name;
    }
}