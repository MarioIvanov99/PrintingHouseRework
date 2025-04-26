package org.printinghouse.model.strategy;

import org.printinghouse.model.Newspaper;
import org.printinghouse.model.Publication;
import org.printinghouse.util.Constants;

import java.math.BigDecimal;

public class NewspaperPrintingCostStrategy implements PrintingCostStrategy {
    @Override
    public BigDecimal calculatePrintingCost(Publication publication, BigDecimal pageCost) {
        Newspaper newspaper = (Newspaper) publication;  // Cast to Book
        return pageCost.multiply(new BigDecimal(newspaper.getNumberOfPages()/2)).add(Constants.NEWSPAPER_PRICE);
    }
}
