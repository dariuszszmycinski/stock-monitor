package dasz.model.stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Beta40Tr implements Stock {
    private String ticket = "BETA40TR";
    private double pricePLN;
    private int quantity;
    private double pe;
    private double pb;

    public Beta40Tr(int quantity) {
        this.quantity = quantity;
        try {
            Document doc = Jsoup.connect("https://stooq.pl/q/?s=betam40tr.pl").get();
            double pricePLN = Double.parseDouble(doc.select("body > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td > table > tbody > tr > td > table:nth-child(8) > tbody > tr > td:nth-child(4) > b > font > span").first().toString().replace("<span id=\"aq_betam40tr.pl_c2\">","").replace("</span>",""));
            this.pricePLN = pricePLN;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Document doc = Jsoup.connect("https://stooq.pl/q/?s=mwig40_pe").get();
            double pe = Double.parseDouble(doc.select("body > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td > table > tbody > tr > td > table:nth-child(8) > tbody > tr > td:nth-child(4) > b > font > span").first().toString().replace("<span id=\"aq_mwig40_pe_c3\">","").replace("</span>",""));
            this.pe = pe;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Document doc = Jsoup.connect("https://stooq.pl/q/?s=mwig40_pb").get();
            double pb = Double.parseDouble(doc.select("body > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td > table > tbody > tr > td > table:nth-child(8) > tbody > tr > td:nth-child(4) > b > font > span").first().toString().replace("<span id=\"aq_mwig40_pb_c3\">","").replace("</span>",""));
            this.pb = pb;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getTotal() {
        return (double)Math.round((quantity * pricePLN)*100)/100;
    }

    @Override
    public String toString() {
        return ticket + ", price= " + pricePLN+ "zł, total= " + getTotal() + "zł pe= " + pe + ", pb= " + pb;
    }

    public String getTicket() {
        return ticket;
    }
}


