package com.amazon.ata.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PolyBagTest {
    private BigDecimal bagVolume = BigDecimal.valueOf(500.0);

    private PolyBag bag;

    @BeforeEach
    public void setUp() {
        bag = new PolyBag(bagVolume);
    }

    @Test
    public void canFitItem_nullItem_throwException() {
        // GIVEN

        // WHEN and THEN
        assertThrows(IllegalArgumentException.class,
                () -> bag.canFitItem(null),
                "Expected canFitItem(null) to throw IllegalArgumentException."
        );
    }

    @Test
    public void canFitItem_itemBigger_doesNotFit() {
        // GIVEN
        Item item = Item.builder()
                .withLength(BigDecimal.valueOf(10.0))
                .withWidth(BigDecimal.valueOf(3.0))
                .withHeight(BigDecimal.valueOf(20.0))
                .build();

        // WHEN
        boolean canFit = bag.canFitItem(item);

        // THEN
        assertFalse(canFit, "Item with bigger volume = (length * width * height) than polyBag should not fit in the polyBag.");
    }

    @Test
    public void canFitItem_itemSameVolumeAsBag_doesNotFit() {
        // GIVEN
        Item item = Item.builder()
                .withLength(BigDecimal.valueOf(10.0))
                .withWidth(BigDecimal.valueOf(5.0))
                .withHeight(BigDecimal.valueOf(10.0))
                .build();

        // WHEN
        boolean canFit = bag.canFitItem(item);

        // THEN
        assertFalse(canFit, "Item with the same volume = (length * width * height) than polyBag should not fit in the polyBag.");
    }

    @Test
    public void canFitItem_itemSmallerThanBag_doesFit() {
        // GIVEN
        Item item = Item.builder()
                .withLength(BigDecimal.valueOf(5.0))
                .withWidth(BigDecimal.valueOf(5.0))
                .withHeight(BigDecimal.valueOf(5.0))
                .build();

        // WHEN
        boolean canFit = bag.canFitItem(item);

        // THEN
        assertTrue(canFit, "Item with smaller volume = (length * width * height) than polyBag should fit in the polyBag.");
    }

    @Test
    public void getMass_calculatesMass_returnsCorrectMass() {
        // GIVEN
        bag = new PolyBag(BigDecimal.valueOf(3600));

        // WHEN
        BigDecimal mass = bag.getMass();

        // THEN
        assertEquals(BigDecimal.valueOf(36.0), mass,
                "Expected mass is 36 for bag with volume 3600.");
    }

    @Test
    public void equals_sameObject_isTrue() {
        // GIVEN
        Packaging anotherBag = new PolyBag(bagVolume);

        // WHEN
        boolean result = anotherBag.equals(anotherBag);

        // THEN
        assertTrue(result, "An object should be equal with itself.");
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        // GIVEN

        // WHEN
        boolean isEqual = bag.equals(null);

        // THEN
        assertFalse(isEqual, "A Packaging should not be equal with null.");
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        // GIVEN
        Object other = "String type!";

        // WHEN
        boolean isEqual = bag.equals(other);

        // THEN
        assertFalse(isEqual, "A Packaging should not be equal to an object of a different type.");
    }

    @Test
    public void equals_sameAttributes_returnsTrue() {
        // GIVEN
        Object other = new PolyBag(bagVolume);

        // WHEN
        boolean isEqual = bag.equals(other);

        // THEN
        assertTrue(isEqual, "Packaging with the same attributes should be equal.");
    }

}
