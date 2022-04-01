package com.amazon.ata.datastore;

import com.amazon.ata.types.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PackagingDatastoreTest {

    FulfillmentCenter ind1 = new FulfillmentCenter("IND1");
    FulfillmentCenter abe2 = new FulfillmentCenter("ABE2");
    FulfillmentCenter yow4 = new FulfillmentCenter("YOW4");
    FulfillmentCenter iad2 = new FulfillmentCenter("IAD2");
    FulfillmentCenter pdx1 = new FulfillmentCenter("PDX1");

    Packaging package10Cm = new Box(BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(10));

    Packaging package20Cm = new Box(BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20));

    Packaging package40Cm = new Box(BigDecimal.valueOf(40), BigDecimal.valueOf(40), BigDecimal.valueOf(40));

    Packaging package60Cm = new Box(BigDecimal.valueOf(60), BigDecimal.valueOf(60), BigDecimal.valueOf(60));

    Packaging package2k = new PolyBag(BigDecimal.valueOf(2000));

    Packaging package10k = new PolyBag(BigDecimal.valueOf(10000));

    Packaging package5k = new PolyBag(BigDecimal.valueOf(5000));

    Packaging package6k = new PolyBag(BigDecimal.valueOf(6000));

    FcPackagingOption ind1_10Cm = new FcPackagingOption(ind1, package10Cm);
    FcPackagingOption abe2_20Cm = new FcPackagingOption(abe2, package20Cm);
    FcPackagingOption abe2_40Cm = new FcPackagingOption(abe2, package40Cm);
    FcPackagingOption yow4_10Cm = new FcPackagingOption(yow4, package10Cm);
    FcPackagingOption yow4_20Cm = new FcPackagingOption(yow4, package20Cm);
    FcPackagingOption yow4_60Cm = new FcPackagingOption(yow4, package60Cm);
    FcPackagingOption iad2_20Cm = new FcPackagingOption(iad2, package20Cm);
    FcPackagingOption pdx1_40Cm = new FcPackagingOption(pdx1, package40Cm);
    FcPackagingOption pdx1_60Cm = new FcPackagingOption(pdx1, package60Cm);

    FcPackagingOption iad2_2k = new FcPackagingOption(iad2, package2k);
    FcPackagingOption iad2_10k = new FcPackagingOption(iad2, package10k);
    FcPackagingOption iad2_5k = new FcPackagingOption(iad2, package5k);

    FcPackagingOption yow4_2k = new FcPackagingOption(yow4, package2k);
    FcPackagingOption yow4_10k = new FcPackagingOption(yow4, package10k);
    FcPackagingOption yow4_5k = new FcPackagingOption(yow4, package5k);

    FcPackagingOption ind1_2k = new FcPackagingOption(ind1, package2k);
    FcPackagingOption ind1_5k = new FcPackagingOption(ind1, package5k);

    FcPackagingOption abe2_2k = new FcPackagingOption(abe2, package2k);
    FcPackagingOption abe2_6k = new FcPackagingOption(abe2, package6k);

    FcPackagingOption pdx1_5k = new FcPackagingOption(pdx1, package5k);
    FcPackagingOption pdx1_10k = new FcPackagingOption(pdx1, package10k);



    @Test
    public void getFcPackagingOptions_get_returnAllOptions() {
        // GIVEN
        PackagingDatastore packagingDatastore = new PackagingDatastore();
        List<FcPackagingOption> expectedPackagingOptions = Arrays.asList(ind1_10Cm, abe2_20Cm, abe2_40Cm, yow4_10Cm,
                yow4_20Cm, yow4_60Cm, iad2_20Cm, iad2_20Cm, pdx1_40Cm, pdx1_60Cm, pdx1_60Cm, iad2_2k, iad2_10k, iad2_5k,
                yow4_2k, yow4_10k, yow4_5k, ind1_2k, ind1_5k, abe2_2k, abe2_6k, pdx1_5k, pdx1_10k, yow4_5k);

        // WHEN
        List<FcPackagingOption> fcPackagingOptions = packagingDatastore.getFcPackagingOptions();

        // THEN
        assertEquals(expectedPackagingOptions.size(), fcPackagingOptions.size(),
                String.format("There should be %s FC/Packaging pairs.", expectedPackagingOptions.size()));
        for (FcPackagingOption expectedPackagingOption : expectedPackagingOptions) {
            assertTrue(fcPackagingOptions.contains(expectedPackagingOption), String.format("expected packaging " +
                            "options from PackagingDatastore to contain %s package in fc %s",
                    expectedPackagingOption.getPackaging(),
                    expectedPackagingOption.getFulfillmentCenter().getFcCode()));
        }
        assertTrue(true, "getFcPackagingOptions contained all of the expected options.");
    }
}
