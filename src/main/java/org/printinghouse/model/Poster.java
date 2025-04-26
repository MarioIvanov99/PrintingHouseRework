package org.printinghouse.model;

import lombok.Getter;
import lombok.Setter;
import org.printinghouse.model.strategy.PosterPrintingCostStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class Poster extends Publication {

    public Poster() {
        this.printingCostStrategy = new PosterPrintingCostStrategy();
    }
    public Poster(String title, LocalDate publishingDate, PaperSize paperSize, BigDecimal salePrice) {
        super(title, publishingDate, paperSize, salePrice);
        this.printingCostStrategy = new PosterPrintingCostStrategy();
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + '\'' +
                ", publishingDate=" + getPublishingDate() +
                ", paperSize=" + getPaperSize() +
                ", salePrice=" + getSalePrice() +
                '}';
    }
}