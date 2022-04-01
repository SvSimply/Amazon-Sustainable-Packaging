package com.amazon.ata.cost;

import com.amazon.ata.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarbonCostStrategyTest {
    private static final Packaging BOX_10x10x20 =
            new Box(BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(20));
    private static final Packaging POLYBAG_3600 = new PolyBag(BigDecimal.valueOf(3600));

    private CarbonCostStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new CarbonCostStrategy();
    }

    @Test
    void getCost_corrugateMaterial_returnsCorrectCost() {
        // GIVEN
        ShipmentOption option = ShipmentOption.builder()
                .withPackaging(BOX_10x10x20)
                .build();

        // WHEN
        ShipmentCost shipmentCost = strategy.getCost(option);

        // THEN
        assertTrue(BigDecimal.valueOf(17.0).compareTo(shipmentCost.getCost()) == 0,
                "Incorrect monetary cost calculation for a box with dimensions 10x10x20."+ shipmentCost.getCost());
    }

    @Test
    void getCost_laminatedPlasticMaterial_returnsCorrectCost() {
        // GIVEN
        ShipmentOption option = ShipmentOption.builder()
                .withPackaging(POLYBAG_3600)
                .build();

        // WHEN
        ShipmentCost shipmentCost = strategy.getCost(option);

        // THEN
        assertTrue(BigDecimal.valueOf(0.432).compareTo(shipmentCost.getCost()) == 0,
                "Incorrect monetary cost calculation for a polybag with volume 3600."+ shipmentCost.getCost());
    }

}
