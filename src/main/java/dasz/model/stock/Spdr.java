package dasz.model.stock;

import dasz.model.currency.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Spdr implements Stock{
    private String ticket;
    private String linkSuffix;
    private double price;
    private Currency currency;
    private double currentPricePLN;
    private double pe;
    private double pb;

    public Spdr(String ticket, String linkSuffix) {
        this.ticket = ticket;
        this.linkSuffix = linkSuffix;
        try {
            Document doc = Jsoup.connect("https://www.ssga.com/uk/en_gb/institutional/etfs/funds/"+linkSuffix).get();
            try {
                String pricePath = doc.select("#overview > div > div.fundsnapshot.aem-GridColumn.aem-GridColumn--default--12 > div > div.aem-GridColumn.aem-GridColumn--default--9.aem-GridColumn--phone--12 > div > div:nth-child(1) > p.points").toString();
                String pricePathNoCurrency = null;
                if (pricePath.contains("$")) {
                    this.currency = Currency.USD;
                    pricePathNoCurrency = pricePath.replace("$", "");
                } else if (pricePath.contains("€")) {
                    this.currency = Currency.EUR;
                    pricePathNoCurrency = pricePath.replace("€", "");
                } else if (pricePath.contains("£")) {
                    this.currency = Currency.GBP;
                    pricePathNoCurrency = pricePath.replace("£", "");
                }
                double price = Double.parseDouble(pricePathNoCurrency.replace("<p class=\"points\">", "").replace("</p>", ""));
                this.price = price;
                double pe = Double.parseDouble(doc.select("#overview > div > div:nth-child(8) > div > div > div.table-items > table.ssmp-d-mobile-none.ssmp-d-tablet-none > tbody > tr > td:nth-child(4)").toString().replace("<td>", "").replace("</td>", ""));
                double pb = Double.parseDouble(doc.select("#overview > div > div:nth-child(8) > div > div > div.table-items > table.ssmp-d-mobile-none.ssmp-d-tablet-none > tbody > tr > td:nth-child(3)").toString().replace("<td>", "").replace("</td>", ""));
                this.pb = pb;
                this.pe = pe;
            } catch (NumberFormatException e) {
                this.pb = 0;
                this.pe = 0;
            }

        } catch (IOException e) {
            System.err.println(ticket);
            e.printStackTrace();
        }
        this.currentPricePLN = (double) Math.round((currency.getValue() * price) * 100) / 100;
        System.out.println(this.toString());
    }

    @Override
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

    public double getPb() {
        return pb;
    }

    @Override
    public String toString() {
        return ticket + ", price= " + price + currency.getSymbol() + " pe= " + pe + ", pb= " + pb;
    }
}
