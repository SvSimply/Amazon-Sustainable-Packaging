package com.amazon.ata.service;

import com.amazon.ata.cost.CostStrategy;
import com.amazon.ata.cost.MonetaryCostStrategy;
import com.amazon.ata.dao.PackagingDAO;
import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.exceptions.NoPackagingFitsItemException;
import com.amazon.ata.exceptions.UnknownFulfillmentCenterException;
import com.amazon.ata.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

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

    private ShipmentOption option = ShipmentOption.builder()
            .withPackaging(new Box(BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(20)))
            .build();

    private FulfillmentCenter existentFC = new FulfillmentCenter("ABE2");
    private FulfillmentCenter nonExistentFC = new FulfillmentCenter("NonExistentFC");
    private CostStrategy costStrategy = new MonetaryCostStrategy();

    @Mock
    private PackagingDAO packagingDAO;


    @InjectMocks
    private ShipmentService shipmentService = new ShipmentService(packagingDAO, costStrategy);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void findBestShipmentOption_existentFCAndItemCanFit_returnsShipmentOption()
            throws NoPackagingFitsItemException, UnknownFulfillmentCenterException {
        // GIVEN & WHEN
//        List<ShipmentCost> shipmentCosts = Arrays.asList(shipmentCost1, shipmentCost2);
//        ShipmentOption shipmentOption =
//        List<ShipmentOption> results = Arrays.asList(
        when(packagingDAO.findShipmentOptions(any(Item.class), any(FulfillmentCenter.class)))
                .thenReturn(Arrays.asList(option));
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(smallItem, existentFC);

        // THEN
        assertNotNull(shipmentOption, "shipmentOption value was" + shipmentOption.toString());
    }

    @Test
    void findBestShipmentOption_existentFCAndItemCannotFit_throwsNoPackagingFitsItemException()
            throws UnknownFulfillmentCenterException, NoPackagingFitsItemException {
        // GIVEN & WHEN & THEN
        when(packagingDAO.findShipmentOptions(any(Item.class), any(FulfillmentCenter.class)))
                .thenThrow(NoPackagingFitsItemException.class);

        assertThrows(NoPackagingFitsItemException.class, () -> {
                    shipmentService.findShipmentOption(largeItem, existentFC);
                }, "When no packaging can fit the item, throw NoPackagingFitsItemException.");

    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCanFit_throwsRuntimeException()
            throws UnknownFulfillmentCenterException, NoPackagingFitsItemException {
        // GIVEN & WHEN
        when(packagingDAO.findShipmentOptions(any(Item.class), any(FulfillmentCenter.class)))
                .thenThrow(UnknownFulfillmentCenterException.class);

        assertThrows(RuntimeException.class, () -> {
            shipmentService.findShipmentOption(smallItem, nonExistentFC);
        }, "When FC doesn't exist, throw RuntimeException.");
    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCannotFit_throwsRuntimeException()
            throws UnknownFulfillmentCenterException, NoPackagingFitsItemException {
        // GIVEN & WHEN
        when(packagingDAO.findShipmentOptions(any(Item.class), any(FulfillmentCenter.class)))
                .thenThrow(UnknownFulfillmentCenterException.class);

        assertThrows(RuntimeException.class, () -> {
            shipmentService.findShipmentOption(largeItem, nonExistentFC);
        }, "When FC doesn't exist, throw RuntimeException.");
    }
}