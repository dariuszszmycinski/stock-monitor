package dasz.model.stock;

import dasz.model.currency.EurToPln;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class JustEtf implements Stock {
    private String ticket;
    private String isin;
    private double priceEUR;
    private int quantity;
    private LocalDate bought;
    private double basePricePLN;
    private double total;
    private double totalGrowth;
    private double annualGrowth;
    private double profit;

    public JustEtf(String ticket, String isin, int quantity, double basePricePLN, LocalDate bought) {
        this.ticket = ticket;
        this.isin = isin;
        this.quantity = quantity;
        this. bought = bought;
        this.basePricePLN = basePricePLN;
        try {
            Document doc = Jsoup.connect("https://www.justetf.com/en/etf-profile.html?isin=" + isin).get();
            double priceEUR = Double.parseDouble((doc.getElementsByClass("val").get(3).toString().replace("<div class=\"val\"> \n" + " <span>EUR</span> \n" + " <span>", "").replace("</span> \n" + "</div>", "")));
            this.priceEUR = priceEUR;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.total=(double)Math.round((quantity * EurToPln.get() * priceEUR)*100)/100;
        this.totalGrowth = (double) Math.round(((getTotal()-basePricePLN)/basePricePLN)*10000)/100;
        this.annualGrowth = (double) Math.round((totalGrowth*365/ ChronoUnit.DAYS.between(bought, LocalDate.now()))*100)/100;
        this.profit = (double) Math.round((total-basePricePLN)*100)/100;
    }

    @Override
    public String toString() {
        return ticket + ", price= " + priceEUR + "€, total= " + getTotal() + "zł"+" totalGrowth= "+totalGrowth+"% "+"annualGrowth= "+annualGrowth+"%"+" profit= "+profit+"zł";
    }

    public String getTicket() {
        return ticket;
    }

    @Override
    public double getTotal() {
        return total;
    }

    public double getTotalGrowth() {
        return totalGrowth;
    }

    public double getAnnualGrowth() {
        return annualGrowth;
    }
}
