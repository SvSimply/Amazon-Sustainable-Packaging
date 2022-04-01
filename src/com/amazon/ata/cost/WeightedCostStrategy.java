package com.amazon.ata.cost;

import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class WeightedCostStrategy implements CostStrategy {
    private Map<CostStrategy, BigDecimal> costStrategyWithWeightMap;

    private WeightedCostStrategy() {    }
    

    /**
     * Creates a new builder for populating a WeightedCostStrategy.
     * @return new builder ready to build.
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        Packaging packaging = shipmentOption.getPackaging();
        BigDecimal cost = new BigDecimal(0);
        for(CostStrategy strategy : costStrategyWithWeightMap.keySet()) {
            cost = cost.add(strategy.getCost(shipmentOption).getCost().multiply(costStrategyWithWeightMap.get(strategy)));
        }

        return new ShipmentCost(shipmentOption, cost);
    }

    public static class Builder {
        private Map<CostStrategy, BigDecimal> costStrategyWithWeightMap = new HashMap<>();

        public Builder addStrategyWithWeight(CostStrategy strategy, BigDecimal weight) {
            this.costStrategyWithWeightMap.put(strategy, weight);
            return this;
        }

        public WeightedCostStrategy build() {
            WeightedCostStrategy strategy = new WeightedCostStrategy();

            strategy.costStrategyWithWeightMap = costStrategyWithWeightMap;

            return strategy;
        }
    }

}
