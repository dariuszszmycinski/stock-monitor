package dasz.model.stock;

import dasz.model.currency.UsdToPln;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Ishares implements Stock {
    private String ticket;
    private int isharesId;
    private double priceUSD;
    private double currentPricePLN;
    private double pe;
    private double pb;

    public Ishares(String ticket, int isharesId) {
        this.ticket = ticket;
        this.isharesId = isharesId;
        try {
            Document doc = Jsoup.connect("https://www.ishares.com/uk/individual/en/products/" + isharesId + "/?siteEntryPassthrough=true").get();
            try{
                double pe = Double.parseDouble(doc.select("#fundamentalsAndRisk > div > div.float-left.in-left.col-priceEarnings > span.data").toString().replace("<span class=\"data\"> ", "").replace(" </span>", ""));
                double pb = Double.parseDouble(doc.select("#fundamentalsAndRisk > div > div.float-left.in-right.col-priceBook > span.data").toString().replace("<span class=\"data\"> ", "").replace(" </span>", ""));
                this.pb = pb;
                this.pe = pe;
            }catch (NumberFormatException e){
                this.pb = 0;
                this.pe = 0;
            }
            //dodać przelicznik eur na usd i rozróżnienie walut, teraz zamieniam znak na $
            double priceUSD = Double.parseDouble(doc.select("#fundheaderTabs > div > div > div > ul > li.navAmount > span.header-nav-data").toString().replace("SEK","USD").replace("GBP","USD").replace("EUR","USD").replace("<span class=\"header-nav-data\"> USD ", "").replace(" </span>", ""));
            this.priceUSD = priceUSD;
        } catch (IOException e) {
            System.err.println(ticket);
            e.printStackTrace();
        }
        this.currentPricePLN = (double) Math.round((UsdToPln.get() * priceUSD) * 100) / 100;
    }


    @Override
    public String toString() {
        return ticket + ", price= " + priceUSD + "$ pe= " + pe + ", pb= " + pb;
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