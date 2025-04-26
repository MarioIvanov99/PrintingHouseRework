package org.printinghouse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.printinghouse.exception.PublicationNotFoundException;
import org.printinghouse.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class PublicationServiceTestIntegration {

    private PublicationService publicationService;
    private Catalogue catalogue;

    @BeforeEach
    public void setUp() {
        publicationService = new PublicationService();
        catalogue = new Catalogue(new HashSet<>());
    }

    @Test
    public void testGetPrintingCostReturnsCorrectCost_INTEGRATION() {
        Book book = new Book("Test Book", LocalDate.now(), 200, PaperSize.A4, CoverType.HARD_COVER, new BigDecimal("20.00"));
        publicationService.addPublication(catalogue, book);

        BigDecimal cost = publicationService.getPrintingCost(book);
        assertNotNull(cost);
        assertTrue(cost.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    public void testAddPublicationCorrectlyAddsNewPublication_INTEGRATION() {
        Book book = new Book("Test Book", LocalDate.now(), 100, PaperSize.A4, CoverType.SOFT_COVER, new BigDecimal("15.00"));

        publicationService.addPublication(catalogue, book);
        assertTrue(catalogue.getPublications().contains(book));
    }

    @Test
    public void testRemovePublicationByIdRemovesCorrectPublication_INTEGRATION() {
        Book book = new Book("Test Book", LocalDate.now(), 150, PaperSize.A5, CoverType.SOFT_COVER, new BigDecimal("18.00"));
        publicationService.addPublication(catalogue, book);

        publicationService.removePublicationById(catalogue, book.getId());
        assertFalse(catalogue.getPublications().contains(book));
    }

    @Test
    public void testRemovePublicationByIdThrowsExceptionWhenPublicationNotFound_INTEGRATION() {
        assertThrows(PublicationNotFoundException.class, () -> publicationService.removePublicationById(catalogue, 999));
    }

    @Test
    public void testGetPublicationByIdReturnsCorrectPublication_INTEGRATION() {
        Book book = new Book("Test Book", LocalDate.now(), 120, PaperSize.A3, CoverType.HARD_COVER, new BigDecimal("25.00"));
        publicationService.addPublication(catalogue, book);

        Publication retrieved = publicationService.getPublicationById(catalogue, book.getId());
        assertEquals(book, retrieved);
    }

    @Test
    public void testGetPublicationByIdThrowsExceptionWhenPublicationNotFound_INTEGRATION() {
        assertThrows(PublicationNotFoundException.class, () -> publicationService.getPublicationById(catalogue, 999));
    }
}
