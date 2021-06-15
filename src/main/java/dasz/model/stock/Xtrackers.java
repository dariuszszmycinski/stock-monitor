package dasz.model.stock;

import dasz.model.currency.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Xtrackers implements Stock {
    private String ticket;
    private String isin;
    private double price;
    private Currency currency;
    private double currentPricePLN;
    private double pe;
    private String peTableRow = "11";

    public Xtrackers(String ticket, String isin) {
        this.ticket = ticket;
        this.isin = isin;
        try {
            String url = "https://etf.dws.com/en-gb/"+isin;
            Document doc = Jsoup.connect(url)
                    .cookie("audiences_en-gb","{\"a\":[\"7864d84e-9892-4df4-9d1b-0109d415b8ae\"],\"i\":true,\"e\":\"01/09/2029 12:18\"}")
                    .get();
            String pricePath = doc.select("#emea-pdp-container > div.col-xl-12.product-box.sticky-header-wrapper > div.row.sticky-header.half > div.col-xl-6.col-lg-12.product-box__nav.no-padding.hide-detached > div:nth-child(1) > div > p.h2").toString();
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
            double price = Double.parseDouble(pricePathNoCurrency.replace("<p class=\"h2\">", "").replace(" </p>", ""));
            this.price = price;
            double pe = Double.parseDouble(doc.select("#emea-pdp-container > section:nth-child(4) > div:nth-child(5) > div > div:nth-child(2) > table > tbody > tr:nth-child("+peTableRow+") > td").toString().replace("<td>", "").replace("</td>", ""));

            this.pe = pe;
        } catch (IOException e) {
            System.err.println(ticket);
            e.printStackTrace();
        }
        this.currentPricePLN = (double) Math.round((currency.getValue() * price) * 100) / 100;
        System.out.println(toString());
    }

    public Xtrackers(String ticket, String isin, String peTableRow) {
        this.peTableRow = peTableRow;
        this.ticket = ticket;
        this.isin = isin;
        try {
            String url = "https://etf.dws.com/en-gb/"+isin;
            Document doc = Jsoup.connect(url)
                    .cookie("audiences_en-gb","{\"a\":[\"7864d84e-9892-4df4-9d1b-0109d415b8ae\"],\"i\":true,\"e\":\"01/09/2029 12:18\"}")
                    .get();
            String pricePath = doc.select("#emea-pdp-container > div.col-xl-12.product-box.sticky-header-wrapper > div.row.sticky-header.half > div.col-xl-6.col-lg-12.product-box__nav.no-padding.hide-detached > div:nth-child(1) > div > p.h2").toString();
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
            double price = Double.parseDouble(pricePathNoCurrency.replace("<p class=\"h2\">", "").replace(" </p>", ""));
            this.price = price;
            double pe = Double.parseDouble(doc.select("#emea-pdp-container > section:nth-child(4) > div:nth-child(5) > div > div:nth-child(2) > table > tbody > tr:nth-child("+peTableRow+") > td").toString().replace("<td>", "").replace("</td>", ""));

            this.pe = pe;
        } catch (IOException e) {
            System.err.println(ticket);
            e.printStackTrace();
        }
        this.currentPricePLN = (double) Math.round((currency.getValue() * price) * 100) / 100;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return ticket + " price= " + price + currency.getSymbol() + " pe= " + pe;
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
