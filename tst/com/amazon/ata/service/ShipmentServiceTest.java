package com.amazon.ata.service;

import com.amazon.ata.cost.MonetaryCostStrategy;
import com.amazon.ata.dao.PackagingDAO;
import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.exceptions.NoPackagingFitsItemException;
import com.amazon.ata.types.FulfillmentCenter;
import com.amazon.ata.types.Item;
import com.amazon.ata.types.ShipmentOption;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentServiceTest {

    private Item smallItem = Item.builder()
            .withHeight(BigDecimal.valueOf(1))
            .withWidth(BigDecimal.valueOf(1))
            .withLength(BigDecimal.valueOf(1))
            .withAsin("abcde")
            .build();

    private Item largeItem = Item.builder()
            .withHeight(BigDecimal.valueOf(1000))
            .withWidth(BigDecimal.valueOf(1000))
            .withLength(BigDecimal.valueOf(1000))
            .withAsin("12345")
            .build();

    private FulfillmentCenter existentFC = new FulfillmentCenter("ABE2");
    private FulfillmentCenter nonExistentFC = new FulfillmentCenter("NonExistentFC");

    private ShipmentService shipmentService = new ShipmentService(new PackagingDAO(new PackagingDatastore()),
            new MonetaryCostStrategy());

    @Test
    void findBestShipmentOption_existentFCAndItemCanFit_returnsShipmentOption() throws NoPackagingFitsItemException {
        // GIVEN & WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(smallItem, existentFC);

        // THEN
        assertNotNull(shipmentOption);
    }

    @Test
    void findBestShipmentOption_existentFCAndItemCannotFit_throwsNoPackagingFitsItemException() {
        // GIVEN & WHEN & THEN
        assertThrows(NoPackagingFitsItemException.class, () -> {
                    shipmentService.findShipmentOption(largeItem, existentFC);
                }, "When no packaging can fit the item, throw NoPackagingFitsItemException.");

    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCanFit_throwsRuntimeException() {
        // GIVEN & WHEN
        assertThrows(RuntimeException.class, () -> {
            shipmentService.findShipmentOption(smallItem, nonExistentFC);
        }, "When FC doesn't exist, throw RuntimeException.");
    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCannotFit_throwsRuntimeException() {
        // GIVEN & WHEN
        assertThrows(RuntimeException.class, () -> {
            shipmentService.findShipmentOption(largeItem, nonExistentFC);
        }, "When FC doesn't exist, throw RuntimeException.");
    }
}