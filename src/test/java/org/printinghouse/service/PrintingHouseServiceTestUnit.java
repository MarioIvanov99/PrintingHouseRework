package org.printinghouse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.printinghouse.model.Catalogue;
import org.printinghouse.model.Order;
import org.printinghouse.model.Publication;
import org.printinghouse.model.strategy.PrintingCostStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrintingHouseServiceTestUnit {

    private PrintingHouseService printingHouseService;

    @Mock
    private PublicationService publicationService;

    @Mock
    private Catalogue catalogue;

    @Mock
    private Order order;

    @Mock
    private Publication publication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        printingHouseService = new PrintingHouseService(publicationService);
    }

    @Test
    void testCalculateExpenseReturnsCorrectCost_UNIT() {
        when(order.getPublicationId()).thenReturn(1);
        when(order.getCopies()).thenReturn(10);
        when(publicationService.getPublicationById(catalogue, 1)).thenReturn(publication);
        when(publicationService.getPrintingCost(publication)).thenReturn(new BigDecimal("5.00"));

        BigDecimal result = printingHouseService.calculateExpense(catalogue, List.of(order));
        assertEquals(new BigDecimal("50.00"), result);

        verify(order).getPublicationId();
        verify(order).getCopies();
        verify(publicationService).getPublicationById(catalogue, 1);
        verify(publicationService).getPrintingCost(publication);
    }

    @Test
    void testCalculateIncomeReturnsCorrectIncome_UNIT() {
        when(order.getPublicationId()).thenReturn(1);
        when(order.getCopies()).thenReturn(10);
        when(publicationService.getPublicationById(catalogue, 1)).thenReturn(publication);
        when(publication.getSalePrice()).thenReturn(new BigDecimal("15.00"));

        BigDecimal result = printingHouseService.calculateIncome(catalogue, List.of(order));
        assertEquals(new BigDecimal("150.00"), result);

        verify(order).getPublicationId();
        verify(order).getCopies();
        verify(publicationService).getPublicationById(catalogue, 1);
        verify(publication).getSalePrice();
    }

    @Test
    void testCalculateProfitReturnsCorrectProfit_UNIT() {
        when(order.getPublicationId()).thenReturn(1);
        when(order.getCopies()).thenReturn(10);
        when(publicationService.getPublicationById(catalogue, 1)).thenReturn(publication);
        when(publicationService.getPrintingCost(publication)).thenReturn(new BigDecimal("5.00"));
        when(publication.getSalePrice()).thenReturn(new BigDecimal("15.00"));

        BigDecimal result = printingHouseService.calculateProfit(catalogue, List.of(order));
        assertEquals(new BigDecimal("100.00"), result);

        verify(order, times(2)).getPublicationId();
        verify(order, times(2)).getCopies();
        verify(publicationService, times(2)).getPublicationById(catalogue, 1);
        verify(publicationService).getPrintingCost(publication);
        verify(publication).getSalePrice();
    }
}
