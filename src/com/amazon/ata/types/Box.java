package com.amazon.ata.types;

import java.math.BigDecimal;

public class Box extends Packaging {
    /**
     * This packaging's smallest dimension.
     */
    private BigDecimal width;

    /**
     * This packaging's length.
     */
    private BigDecimal length;

    /**
     * This packaging's largest dimension.
     */
    private BigDecimal height;

    /**
     * Instantiates a new Packaging object.
     *
     * @param length   - the length of the package
     * @param width    - the width of the package
     * @param height   - the height of the package
     */
    public Box(BigDecimal length, BigDecimal width, BigDecimal height) {
        super(Material.CORRUGATE);
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public BigDecimal getLength() {
        return length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    /**
     * Returns whether the given item will fit in this packaging.
     *
     * @param item the item to test fit for
     * @return whether the item will fit in this packaging
     */
    @Override
    public boolean canFitItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item couldn't be null");
        }

        return this.length.compareTo(item.getLength()) > 0 &&
                this.width.compareTo(item.getWidth()) > 0 &&
                this.height.compareTo(item.getHeight()) > 0;
    }

    /**
     * Returns the mass of the packaging in grams. The packaging weighs 1 gram per square centimeter.
     * @return the mass of the packaging
     */
    @Override
    public BigDecimal getMass() {
        BigDecimal two = BigDecimal.valueOf(2);

        // For simplicity, we ignore overlapping flaps
        BigDecimal endsArea = length.multiply(width).multiply(two);
        BigDecimal shortSidesArea = length.multiply(height).multiply(two);
        BigDecimal longSidesArea = width.multiply(height).multiply(two);

        return endsArea.add(shortSidesArea).add(longSidesArea);
    }

    @Override
    public boolean equals(Object o) {
        // Can't be equal to null
        if (o == null) {
            return false;
        }

        // Referentially equal
        if (this == o) {
            return true;
        }

        // Check if it's a different type
        if (getClass() != o.getClass()) {
            return false;
        }

        Box box = (Box) o;
        return this.getHeight().compareTo(box.getHeight()) == 0 &&
                this.getWidth().compareTo(box.getWidth()) == 0 &&
                this.getLength().compareTo(box.getLength()) == 0;
    }
}
