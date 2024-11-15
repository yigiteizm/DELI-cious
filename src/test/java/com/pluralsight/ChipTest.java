package com.pluralsight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ChipTest {

    @Test
    public void calculatePrice_ReturnsFixedPrice() {
        // Arrange
        Chip regularChip = new Chip("Regular");
        Chip bbqChip = new Chip("Barbecue");
        Chip saltVinegarChip = new Chip("Salt & Vinegar");

        // Act & Assert
        assertEquals(1.50, regularChip.calculatePrice(), 0.001, "Regular Chip price should be 1.50");
        assertEquals(1.50, bbqChip.calculatePrice(), 0.001, "Barbecue Chip price should be 1.50");
        assertEquals(1.50, saltVinegarChip.calculatePrice(), 0.001, "Salt & Vinegar Chip price should be 1.50");
    }

}