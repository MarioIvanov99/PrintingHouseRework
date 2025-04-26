package org.printinghouse.service;

import org.printinghouse.exception.PublicationNotFoundException;
import org.printinghouse.model.Catalogue;
import org.printinghouse.model.PaperSize;
import org.printinghouse.model.Publication;
import org.printinghouse.util.Constants;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class PublicationService {

    private static final Map<PaperSize, BigDecimal> PAPER_SIZE_PRICES = new EnumMap<>(PaperSize.class);

    static {
        PAPER_SIZE_PRICES.put(PaperSize.A1, Constants.A1_PRICE);
        PAPER_SIZE_PRICES.put(PaperSize.A2, Constants.A2_PRICE);
        PAPER_SIZE_PRICES.put(PaperSize.A3, Constants.A3_PRICE);
        PAPER_SIZE_PRICES.put(PaperSize.A4, Constants.A4_PRICE);
        PAPER_SIZE_PRICES.put(PaperSize.A5, Constants.A5_PRICE);
    }

    public BigDecimal getPageCost(PaperSize paperSize) {
        return PAPER_SIZE_PRICES.get(paperSize);
    }

    public BigDecimal getPrintingCost(Publication publication) {
        return publication.getPrintingCostStrategy().calculatePrintingCost(publication, getPageCost(publication.getPaperSize()));
    }

    public void addPublication(Catalogue catalogue, Publication publication) {
        catalogue.getPublications().add(publication);
    }

    public void removePublicationById(Catalogue catalogue, int id) {
        boolean removed = catalogue.getPublications().removeIf(pub -> pub.getId() == id);
        if (!removed) {
            throw new PublicationNotFoundException("Publication with ID " + id + " not found.");
        }
    }

    public Publication getPublicationById(Catalogue catalogue, int id) {
        return catalogue.getPublications().stream()
                .filter(pub -> pub.getId() == id)
                .findFirst()
                .orElseThrow(() -> new PublicationNotFoundException("Publication with ID " + id + " not found."));
    }
}
