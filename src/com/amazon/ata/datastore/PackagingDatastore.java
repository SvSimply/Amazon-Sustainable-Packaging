package com.amazon.ata.datastore;

import com.amazon.ata.types.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Stores all configured packaging pairs for all fulfillment centers.
 */
public class PackagingDatastore {

    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    private final List<FcPackagingOption> fcPackagingOptions = Arrays.asList(
            createFcPackagingOption("IND1", new Box(BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(10))),
            createFcPackagingOption("ABE2", new Box(BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20))),
            createFcPackagingOption("ABE2", new Box(BigDecimal.valueOf(40), BigDecimal.valueOf(40), BigDecimal.valueOf(40))),
            createFcPackagingOption("YOW4", new Box(BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(10))),
            createFcPackagingOption("YOW4", new Box(BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20))),
            createFcPackagingOption("YOW4", new Box(BigDecimal.valueOf(60), BigDecimal.valueOf(60), BigDecimal.valueOf(60))),
            createFcPackagingOption("IAD2", new Box(BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20))),
            createFcPackagingOption("IAD2", new Box(BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20))),
            createFcPackagingOption("PDX1", new Box(BigDecimal.valueOf(40), BigDecimal.valueOf(40), BigDecimal.valueOf(40))),
            createFcPackagingOption("PDX1", new Box(BigDecimal.valueOf(60), BigDecimal.valueOf(60), BigDecimal.valueOf(60))),
            createFcPackagingOption("PDX1", new Box(BigDecimal.valueOf(60), BigDecimal.valueOf(60), BigDecimal.valueOf(60))),
            createFcPackagingOption("IAD2", new PolyBag(BigDecimal.valueOf(2000))),
            createFcPackagingOption("IAD2", new PolyBag(BigDecimal.valueOf(10000))),
            createFcPackagingOption("IAD2", new PolyBag(BigDecimal.valueOf(5000))),
            createFcPackagingOption("YOW4", new PolyBag(BigDecimal.valueOf(2000))),
            createFcPackagingOption("YOW4", new PolyBag(BigDecimal.valueOf(5000))),
            createFcPackagingOption("YOW4", new PolyBag(BigDecimal.valueOf(10000))),
            createFcPackagingOption("IND1", new PolyBag(BigDecimal.valueOf(2000))),
            createFcPackagingOption("IND1", new PolyBag(BigDecimal.valueOf(5000))),
            createFcPackagingOption("ABE2", new PolyBag(BigDecimal.valueOf(2000))),
            createFcPackagingOption("ABE2", new PolyBag(BigDecimal.valueOf(6000))),
            createFcPackagingOption("PDX1", new PolyBag(BigDecimal.valueOf(5000))),
            createFcPackagingOption("PDX1", new PolyBag(BigDecimal.valueOf(10000))),
            createFcPackagingOption("YOW4", new PolyBag(BigDecimal.valueOf(5000)))

    );

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    private FcPackagingOption createFcPackagingOption(String fcCode, Packaging packaging) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    public List<FcPackagingOption> getFcPackagingOptions() {
        return fcPackagingOptions;
    }
}
