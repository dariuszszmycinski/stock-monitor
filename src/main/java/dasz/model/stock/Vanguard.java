package dasz.model.stock;

import dasz.model.currency.Currency;
import org.jsoup.Jsoup;

import java.io.IOException;

public class Vanguard implements Stock{
    private String ticket;
    private int portId;
    private double price;
    private Currency currency = Currency.USD;
    private double currentPricePLN;
    private double pe;
    private double pb;

    public Vanguard(String ticket, int portId) {
        this.ticket = ticket;
        this.portId = portId;
        //Correct vanguart site https://www.vanguardinvestments.dk/portal/instl/dk/en/product.html#/fundDetail/etf/portId=9507/assetCode=equity/?overview
        String url = "https://api.vanguard.com/rs/gre/gra/1.7.0/datasets/gas-dk-inst-overview-data-etf.jsonp?vars=portId:"+portId;
        String data = null;
        try {
            data = Jsoup.connect(url).ignoreContentType(true).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            this.price = Double.parseDouble(data.split("\"MKTPPrice\":")[1].substring(0,5).replace(",",""));
            String currencyString = data.split("\",\"MKTPCurrencyCode\":\"")[1].substring(0,3); //currency
            if (currencyString.equals("USD")) {
                this.currency = Currency.USD;
            } else if (currencyString.equals("EUR")) {
                this.currency = Currency.EUR;
            } else if (currencyString.equals("GBP")) {
                this.currency = Currency.GBP;
            }
            this.pe = Double.parseDouble(data.split("\"fundPERatio\":")[1].substring(0,5).replace(",",""));
            this.pb = Double.parseDouble(data.split("\"fundPBRatio\":")[1].substring(0,5).replace(",",""));
        }catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            System.out.println(ticket+" brak danych");
        }

        this.currentPricePLN = (double) Math.round((currency.getValue() * price) * 100) / 100;
        System.out.println(this);
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