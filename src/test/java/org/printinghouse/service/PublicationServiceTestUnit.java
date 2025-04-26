package org.printinghouse.service;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.printinghouse.exception.PublicationNotFoundException;
import org.printinghouse.model.Catalogue;
import org.printinghouse.model.PaperSize;
import org.printinghouse.model.Publication;
import org.printinghouse.model.strategy.PrintingCostStrategy;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PublicationServiceTestUnit extends TestCase {

    private PublicationService publicationService;

    @Mock
    private Catalogue catalogue;

    @Mock
    private Publication publication;

    @Mock
    private PrintingCostStrategy printingCostStrategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        publicationService = new PublicationService();
    }

    @Test
    public void testGetPrintingCostReturnsCorrectCost_UNIT() {
        when(publication.getPrintingCostStrategy()).thenReturn(printingCostStrategy);
        when(publication.getPaperSize()).thenReturn(PaperSize.A4);
        when(printingCostStrategy.calculatePrintingCost(any(), any())).thenReturn(new BigDecimal("10.00"));

        BigDecimal cost = publicationService.getPrintingCost(publication);
        assertEquals(new BigDecimal("10.00"), cost);

        verify(publication).getPrintingCostStrategy();
        verify(publication).getPaperSize();
        verify(printingCostStrategy).calculatePrintingCost(any(), any());
    }

    @Test
    public void testAddPublicationCorrectlyAddsNewPublication_UNIT() {
        Set<Publication> publications = new HashSet<>();
        when(catalogue.getPublications()).thenReturn(publications);

        publicationService.addPublication(catalogue, publication);
        assertTrue(publications.contains(publication));

        verify(catalogue).getPublications();
    }

    @Test
    void testRemovePublicationByIdRemovesCorrectPublication_UNIT() {
        Publication pub1 = mock(Publication.class);
        when(pub1.getId()).thenReturn(1);

        Set<Publication> publications = new HashSet<>();
        publications.add(pub1);
        when(catalogue.getPublications()).thenReturn(publications);

        publicationService.removePublicationById(catalogue, 1);
        assertFalse(publications.contains(pub1));

        verify(catalogue).getPublications();
    }

    @Test
    void testRemovePublicationByIdThrowsExceptionWhenPublicationNotFound_UNIT() {
        when(catalogue.getPublications()).thenReturn(new HashSet<>());
        assertThrows(PublicationNotFoundException.class, () -> publicationService.removePublicationById(catalogue, 1));

        verify(catalogue).getPublications();
    }

    @Test
    public void testGetPublicationByIdReturnsCorrectPublication_UNIT() {
        Publication pub1 = mock(Publication.class);
        when(pub1.getId()).thenReturn(1);

        Set<Publication> publications = new HashSet<>();
        publications.add(pub1);
        when(catalogue.getPublications()).thenReturn(publications);

        Publication result = publicationService.getPublicationById(catalogue, 1);
        assertEquals(pub1, result);

        verify(catalogue).getPublications();
    }

    @Test
    public void testGetPublicationByIdThrowsExceptionWhenPublicationNotFound_UNIT() {
        when(catalogue.getPublications()).thenReturn(new HashSet<>());
        assertThrows(PublicationNotFoundException.class, () -> publicationService.getPublicationById(catalogue, 1));

        verify(catalogue).getPublications();
    }
}