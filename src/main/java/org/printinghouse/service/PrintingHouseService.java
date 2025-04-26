package org.printinghouse.service;

import org.printinghouse.model.Catalogue;
import org.printinghouse.model.Order;
import org.printinghouse.model.Publication;
import org.printinghouse.util.Constants;

import java.math.BigDecimal;
import java.util.List;

public class PrintingHouseService {

    PublicationService publicationService;

    public PrintingHouseService(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    public BigDecimal calculateExpense(Catalogue catalogue, List<Order> orders) {
        BigDecimal expense = new BigDecimal("0.00");
        for (Order order : orders) {
            Publication publication = publicationService.getPublicationById(catalogue, order.getPublicationId());

            expense = expense.add(
                    publicationService.getPrintingCost(publication)
                    .multiply(new BigDecimal(order.getCopies()))
            );
        }

        return expense;
    }

    public BigDecimal calculateIncome(Catalogue catalogue, List<Order> orders) {
        BigDecimal income = new BigDecimal("0.00");
        for (Order order : orders) {
            income = income.add(
                    publicationService.getPublicationById( catalogue,
                    order.getPublicationId()).getSalePrice()
                    .multiply(new BigDecimal(order.getCopies()))
            );
        }

        return income;
    }

    public BigDecimal calculateProfit(Catalogue catalogue, List<Order> orders) {
        return calculateIncome(catalogue, orders).subtract(calculateExpense(catalogue, orders));
    }

    public BigDecimal calculateNetProfit(Catalogue catalogue, List<Order> orders) {
        BigDecimal afterTaxMultiplier = new BigDecimal("1.0").subtract(Constants.BULGARIAN_TAX);
        return calculateProfit(catalogue, orders).multiply(afterTaxMultiplier);
    }
}
