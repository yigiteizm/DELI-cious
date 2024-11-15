package com.pluralsight;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SauceTest {

    @Test
    public void calculatePrice_ReturnsZeroForAllSizes() {
        // Arrange
        Sauce mayoSauce = new Sauce("Mayo");

        // Act & Assert
        assertEquals(0.0, mayoSauce.calculatePrice(1), "Price should be zero for any size");
        assertEquals(0.0, mayoSauce.calculatePrice(5), "Price should be zero for any size");
    }
}