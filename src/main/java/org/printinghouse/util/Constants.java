package org.printinghouse.util;

import java.math.BigDecimal;

public class Constants {
    //Data
    public static final String PUBLICATIONS_FILE_PATH = "input/publications.json";
    public static final String ORDERS_FILE_PATH = "input/orders.json";

    //Tax
    public static final BigDecimal BULGARIAN_TAX = new BigDecimal("0.10");

    //Paper price
    public static final BigDecimal A1_PRICE = new BigDecimal("2.00");
    public static final BigDecimal A2_PRICE = new BigDecimal("0.50");
    public static final BigDecimal A3_PRICE = new BigDecimal("0.10");
    public static final BigDecimal A4_PRICE = new BigDecimal("0.05");
    public static final BigDecimal A5_PRICE = new BigDecimal("0.01");

    //Printing price
    public static final BigDecimal POSTER_PRICE = new BigDecimal("2.00");
    public static final BigDecimal BOOK_PRICE = new BigDecimal("3.00");
    public static final BigDecimal NEWSPAPER_PRICE = new BigDecimal("1.00");
    public static final BigDecimal SOFT_COVER_PRICE = new BigDecimal("0.10");
    public static final BigDecimal HARD_COVER_PRICE = new BigDecimal("0.20");
}
