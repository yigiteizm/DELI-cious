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
}