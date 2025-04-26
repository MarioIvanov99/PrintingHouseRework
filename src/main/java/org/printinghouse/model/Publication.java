package org.printinghouse.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.printinghouse.model.strategy.PrintingCostStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "Book"),
        @JsonSubTypes.Type(value = Newspaper.class, name = "Newspaper"),
        @JsonSubTypes.Type(value = Poster.class, name = "Poster")
})
public abstract class Publication {
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    private final int id = idCounter.getAndIncrement();;
    private String title;
    private LocalDate publishingDate;
    private PaperSize paperSize;
    private BigDecimal salePrice;
    protected PrintingCostStrategy printingCostStrategy;

    public Publication(String title, LocalDate publishingDate, PaperSize paperSize, BigDecimal salePrice) {
        this.title = title;
        this.publishingDate = publishingDate;
        this.paperSize = paperSize;
        this.salePrice = salePrice;
    }
}