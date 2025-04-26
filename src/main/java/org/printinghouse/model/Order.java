package org.printinghouse.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
public class Order {
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    private final int id = idCounter.getAndIncrement();
    private int publicationId;
    private int copies;

    public Order(int publicationId, int copies) {
        this.publicationId = publicationId;
        this.copies = copies;
    }
}