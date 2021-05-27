package dasz.model.stock;

import dasz.model.currency.UsdToPln;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Ishares implements Stock{
    private String ticket;
    private int isharesId;
    private double priceUSD;
    private int quantity;
    private LocalDate bought;
    private double basePricePLN;
    private double pe;
    private double pb;
    private double total;
    private double totalGrowth;
    private double annualGrowth;
    private double profit;

    public Ishares(String ticket, int isharesId, int quantity, double basePricePLN, LocalDate bought) {
        this.ticket = ticket;
        this.isharesId = isharesId;
        this.quantity = quantity;
        this. bought = bought;
        this.basePricePLN = basePricePLN;
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
        this.total = (double)Math.round((quantity * UsdToPln.get() * priceUSD)*100)/100;
        this.totalGrowth = (double) Math.round(((getTotal()-basePricePLN)/basePricePLN)*10000)/100;
        this.annualGrowth = (double) Math.round((totalGrowth*365/ ChronoUnit.DAYS.between(bought, LocalDate.now()))*100)/100;
        this.profit = (double) Math.round((total-basePricePLN)*100)/100;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return ticket + ", price= " + priceUSD + "$, total= " + getTotal() + "zł pe= " + pe + ", pb= " + pb+" totalGrowth= "+totalGrowth+"% "+"annualGrowth= "+annualGrowth+"%"+" profit= "+profit+"zł";
    }

    public String getTicket() {
        return ticket;
    }

    public double getTotalGrowth() {
        return totalGrowth;
    }

    public double getAnnualGrowth() {
        return annualGrowth;
    }
}
