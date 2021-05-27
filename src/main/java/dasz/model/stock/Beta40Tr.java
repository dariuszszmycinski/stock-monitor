package dasz.model.stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Beta40Tr implements Stock {
    private String ticket = "BETA40TR";
    private LocalDate bought;
    private double basePricePLN;
    private double currentPricePLN;
    private int quantity;
    private double pe;
    private double pb;
    private double total;
    private double totalGrowth;
    private double annualGrowth;
    private double profit;

    public Beta40Tr(int quantity, double basePricePLN, LocalDate bought) {
        this. bought = bought;
        this.basePricePLN = basePricePLN;
        this.quantity = quantity;
        try {
            Document doc = Jsoup.connect("https://stooq.pl/q/?s=betam40tr.pl").get();
            this.currentPricePLN = Double.parseDouble(doc.select("body > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td > table > tbody > tr > td > table:nth-child(8) > tbody > tr > td:nth-child(4) > b > font > span").first().toString().replace("<span id=\"aq_betam40tr.pl_c2\">","").replace("</span>",""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Document doc = Jsoup.connect("https://stooq.pl/q/?s=mwig40_pe").get();
            this.pe = Double.parseDouble(doc.select("body > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td > table > tbody > tr > td > table:nth-child(8) > tbody > tr > td:nth-child(4) > b > font > span").first().toString().replace("<span id=\"aq_mwig40_pe_c3\">","").replace("</span>",""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Document doc = Jsoup.connect("https://stooq.pl/q/?s=mwig40_pb").get();
            this.pb = Double.parseDouble(doc.select("body > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td > table > tbody > tr > td > table:nth-child(8) > tbody > tr > td:nth-child(4) > b > font > span").first().toString().replace("<span id=\"aq_mwig40_pb_c3\">","").replace("</span>",""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.total=(double)Math.round((quantity * currentPricePLN)*100)/100;
        this.totalGrowth = (double) Math.round(((getTotal()-basePricePLN)/basePricePLN)*10000)/100;
        this.annualGrowth = (double) Math.round((totalGrowth*365/ChronoUnit.DAYS.between(bought, LocalDate.now()))*100)/100;
        this.profit = (double) Math.round((total-basePricePLN)*100)/100;
    }


    @Override
    public String toString() {
        return ticket + ", price= " + currentPricePLN + "zł, total= " + getTotal() + "zł pe= " + pe + ", pb= " + pb+" totalGrowth= "+totalGrowth+"% "+"annualGrowth= "+annualGrowth+"%"+" profit= "+profit+"zł";
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


