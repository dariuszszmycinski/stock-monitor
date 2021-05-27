package dasz.model.stock;

import dasz.model.currency.UsdToPln;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Ishares implements Stock{
    private String ticket;
    private int isharesId;
    private double priceUSD;
    private int quantity;
    private double pe;
    private double pb;

    public Ishares(String ticket, int isharesId, int quantity) {
        this.ticket = ticket;
        this.isharesId = isharesId;
        this.quantity = quantity;
        try {
            Document doc = Jsoup.connect("https://www.ishares.com/uk/individual/en/products/" + isharesId + "/?siteEntryPassthrough=true").get();
            double pe = Double.parseDouble(doc.select("#fundamentalsAndRisk > div > div.float-left.in-left.col-priceEarnings > span.data").toString().replace("<span class=\"data\"> ", "").replace(" </span>", ""));
            this.pe = pe;
            double pb = Double.parseDouble(doc.select("#fundamentalsAndRisk > div > div.float-left.in-right.col-priceBook > span.data").toString().replace("<span class=\"data\"> ", "").replace(" </span>", ""));
            this.pb = pb;
            double priceUSD = Double.parseDouble(doc.select("#fundheaderTabs > div > div > div > ul > li.navAmount > span.header-nav-data").toString().replace("<span class=\"header-nav-data\"> USD ", "").replace(" </span>", ""));
            this.priceUSD = priceUSD;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getTotal() {
        return (double)Math.round((quantity * UsdToPln.get() * priceUSD)*100)/100;
    }

    @Override
    public String toString() {
        return ticket + ", price= " + priceUSD + "$, total= " + getTotal() + "zł pe= " + pe + ", pb= " + pb;
    }

    public String getTicket() {
        return ticket;
    }
}
