package dasz.model.stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Beta40Tr implements Stock {
    private String ticket = "BETA40TR";
    private double currentPricePLN;
    private double pe;
    private double pb;

    public Beta40Tr() {
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
    }

    @Override
    public String toString() {
        return ticket + ", price= " + currentPricePLN + "zł, pe= " + pe + ", pb= " + pb;
    }

    public String getTicket() {
        return ticket;
    }

    public double getCurrentPricePLN() {
        return currentPricePLN;
    }

    @Override
    public double getPe() {
        return pe;
    }
}


