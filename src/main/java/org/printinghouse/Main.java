package org.printinghouse;

import org.printinghouse.model.Catalogue;
import org.printinghouse.model.Order;
import org.printinghouse.service.PrintingHouseService;
import org.printinghouse.service.PublicationService;
import org.printinghouse.util.Constants;
import org.printinghouse.util.JsonLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Catalogue catalogue = null;
        List<Order> orders = null;

        try {
            catalogue = new Catalogue(JsonLoader.loadPublications(Constants.PUBLICATIONS_FILE_PATH));
            orders = JsonLoader.loadOrders(Constants.ORDERS_FILE_PATH);
        }catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        PrintingHouseService printingHouseService = new PrintingHouseService(new PublicationService());

        System.out.println("Total expenses: " + printingHouseService.calculateExpense(catalogue, orders));
        System.out.println("Total income: " + printingHouseService.calculateIncome(catalogue, orders));
        System.out.println("Profit: " + printingHouseService.calculateProfit(catalogue, orders));
        System.out.println("Profit after tax: " + printingHouseService.calculateNetProfit(catalogue, orders));
    }
}