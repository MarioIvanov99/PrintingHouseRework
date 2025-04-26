package org.printinghouse.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.printinghouse.model.strategy.NewspaperPrintingCostStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class Newspaper extends Publication {
    private int numberOfPages;

    public Newspaper() {
        this.printingCostStrategy = new NewspaperPrintingCostStrategy();
    }
    public Newspaper(String title, LocalDate publishingDate, int sheets, PaperSize paperSize, BigDecimal salePrice) {
        super(title, publishingDate, paperSize, salePrice);
        this.numberOfPages = sheets;
        this.printingCostStrategy = new NewspaperPrintingCostStrategy();
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + '\'' +
                ", publishingDate=" + getPublishingDate() +
                ", numberOfPages=" + numberOfPages +
                ", paperSize=" + getPaperSize() +
                ", salePrice=" + getSalePrice() +
                '}';
    }
}
