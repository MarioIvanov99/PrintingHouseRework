package org.printinghouse.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import org.printinghouse.model.strategy.BookPrintingCostStrategy;

@Getter
@Setter
public class Book extends Publication {
    private CoverType coverType;
    private int numberOfPages;

    public Book() {
        this.printingCostStrategy = new BookPrintingCostStrategy();
    }

    public Book(String title, LocalDate publishingDate, int numberOfPages, PaperSize paperSize, CoverType coverType, BigDecimal salePrice) {
        super(title, publishingDate, paperSize, salePrice);
        this.numberOfPages = numberOfPages;
        this.coverType = coverType;
        this.printingCostStrategy = new BookPrintingCostStrategy();
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + '\'' +
                ", publishingDate=" + getPublishingDate() +
                ", numberOfPages=" + numberOfPages +
                ", paperSize=" + getPaperSize() +
                ", coverType=" + coverType +
                ", salePrice=" + getSalePrice() +
                '}';
    }
}