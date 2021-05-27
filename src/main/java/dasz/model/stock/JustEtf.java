package dasz.model.stock;

import dasz.model.currency.EurToPln;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JustEtf implements Stock {
    private String ticket;
    private String isin;
    private double priceEUR;
    private int quantity;

    public JustEtf(String ticket, String isin, int quantity) {
        this.ticket = ticket;
        this.isin = isin;
        this.quantity = quantity;
        try {
            Document doc = Jsoup.connect("https://www.justetf.com/en/etf-profile.html?isin=" + isin).get();
            double priceEUR = Double.parseDouble((doc.getElementsByClass("val").get(3).toString().replace("<div class=\"val\"> \n" + " <span>EUR</span> \n" + " <span>", "").replace("</span> \n" + "</div>", "")));
            this.priceEUR = priceEUR;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getTotal() {
        return (double)Math.round((quantity * EurToPln.get() * priceEUR)*100)/100;
    }

    @Override
    public String toString() {
        return ticket + ", price= " + priceEUR + "€, total= " + getTotal() + "zł";
    }

    public String getTicket() {
        return ticket;
    }
}
