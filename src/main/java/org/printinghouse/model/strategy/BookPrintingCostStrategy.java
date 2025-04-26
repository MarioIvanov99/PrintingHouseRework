package org.printinghouse.model.strategy;

import org.printinghouse.model.Book;
import org.printinghouse.model.CoverType;
import org.printinghouse.model.Publication;
import org.printinghouse.util.Constants;

import java.math.BigDecimal;

public class BookPrintingCostStrategy implements PrintingCostStrategy {

    @Override
    public BigDecimal calculatePrintingCost(Publication publication, BigDecimal pageCost) {
        Book book = (Book) publication;  // Cast to Book
        BigDecimal coverPrice = book.getCoverType() == CoverType.SOFT_COVER ? Constants.SOFT_COVER_PRICE : Constants.HARD_COVER_PRICE;
        return pageCost.multiply(new BigDecimal(book.getNumberOfPages()))
                .add(coverPrice)
                .add(Constants.BOOK_PRICE);
    }
}
