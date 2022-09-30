package dasz.model.stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Stooq implements Stock {
    private String ticket;
    private String stooqId;
    private double currentPricePLN;
    private double pe;
    private double pb;
    private int sitePriceLinkId;

    public Stooq(String ticket, String stooqId, int sitePriceLinkId) {
        this.ticket = ticket;
        this.stooqId = stooqId;
        this.sitePriceLinkId = sitePriceLinkId;
        try {
            Document doc = Jsoup.connect("https://stooq.pl/q/?s=" + stooqId).get();
            String price = doc.select("#aq_" + stooqId + "_c" + sitePriceLinkId).toString().replaceAll("<", ">").split(">")[2];
            this.currentPricePLN = Double.parseDouble(price);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Document doc = Jsoup.connect("https://stooq.pl/q/?s=" + stooqId + "_pe").get();
            this.pe = Double.parseDouble(doc.select("#aq_" + stooqId + "_pe_c2").toString().replaceAll("<", ">").split(">")[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Document doc = Jsoup.connect("https://stooq.pl/q/?s=" + stooqId + "_pb").get();
            this.pb = Double.parseDouble(doc.select("#aq_" + stooqId + "_pb_c2").toString().replaceAll("<", ">").split(">")[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(toString());
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

    @Override
    public double getPb() {
        return pb;
    }
}
