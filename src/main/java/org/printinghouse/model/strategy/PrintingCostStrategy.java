package org.printinghouse.model.strategy;

import org.printinghouse.model.Publication;

import java.math.BigDecimal;

public interface PrintingCostStrategy {
    BigDecimal calculatePrintingCost(Publication publication, BigDecimal pageCost);
}