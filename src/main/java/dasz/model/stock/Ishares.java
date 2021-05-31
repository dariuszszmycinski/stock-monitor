package dasz.model.stock;

import dasz.model.currency.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Ishares implements Stock {
    private String ticket;
    private int isharesId;
    private double price;
    private Currency currency;
    private double currentPricePLN;
    private double pe;
    private double pb;

    public Ishares(String ticket, int isharesId) {
        this.ticket = ticket;
        this.isharesId = isharesId;
        try {
            Document doc = Jsoup.connect("https://www.ishares.com/uk/individual/en/products/" + isharesId + "/?siteEntryPassthrough=true").get();
            try {
                String pricePath = doc.select("#fundheaderTabs > div > div > div > ul > li.navAmount > span.header-nav-data").toString();
                String pricePathNoCurrency = null;
                if (pricePath.contains("USD")) {
                    this.currency = Currency.USD;
                    pricePathNoCurrency = pricePath.replace("USD", "");
                } else if (pricePath.contains("EUR")) {
                    this.currency = Currency.EUR;
                    pricePathNoCurrency = pricePath.replace("EUR", "");
                } else if (pricePath.contains("GBP")) {
                    this.currency = Currency.GBP;
                    pricePathNoCurrency = pricePath.replace("GBP", "");
                }
                double pe = Double.parseDouble(doc.select("#fundamentalsAndRisk > div > div.float-left.in-left.col-priceEarnings > span.data").toString().replace("<span class=\"data\"> ", "").replace(" </span>", ""));
                double pb = Double.parseDouble(doc.select("#fundamentalsAndRisk > div > div.float-left.in-right.col-priceBook > span.data").toString().replace("<span class=\"data\"> ", "").replace(" </span>", ""));
                this.pb = pb;
                this.pe = pe;
                double price = Double.parseDouble(pricePathNoCurrency.replace("<span class=\"header-nav-data\">  ", "").replace(" </span>", ""));
                this.price = price;

            } catch (NumberFormatException e) {
                this.pb = 0;
                this.pe = 0;
            }

        } catch (IOException e) {
            System.err.println(ticket);
            e.printStackTrace();
        }
        this.currentPricePLN = (double) Math.round((currency.getValue() * price) * 100) / 100;
    }

    @Override
    public String toString() {
        return ticket + ", price= " + price + currency.getSymbol() + " pe= " + pe + ", pb= " + pb;
    }

    public String getTicket() {
        return ticket;
    }

    @Override
    public double getCurrentPricePLN() {
        return currentPricePLN;
    }

    @Override
    public double getPe() {
        return pe;
    }
}