package org.printinghouse.model.strategy;

import org.printinghouse.model.Newspaper;
import org.printinghouse.model.Poster;
import org.printinghouse.model.Publication;
import org.printinghouse.util.Constants;

import java.math.BigDecimal;

public class PosterPrintingCostStrategy implements PrintingCostStrategy {
    @Override
    public BigDecimal calculatePrintingCost(Publication publication, BigDecimal pageCost) {
        Poster poster = (Poster) publication;  // Cast to Book
        return pageCost.add(Constants.POSTER_PRICE);
    }
}
