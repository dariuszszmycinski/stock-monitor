package dasz.model.stock;

import dasz.model.currency.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WisdomTree implements Stock{
    private String ticket;
    private String linkSuffix;
    private double price;
    private Currency currency;
    private double currentPricePLN;
    private double pe;
    private double pb;

    public WisdomTree(String ticket, String linkSuffix)  {
        this.ticket = ticket;
        this.linkSuffix = linkSuffix;
        String url = "https://www.wisdomtree.eu/en-gb/etfs/equities/wisdomtree-emerging-markets-equity-income-ucits-etf#";
        try {
            Document doc = Jsoup.connect(url)
                    //.proxy("127.0.0.1", 8080)
                    .userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
                    .header("Content-Language", "en-US")
                    /*    .cookie("incap_ses_687_1092903","w9cfWlio1RRbBMdhObeICT5c3GAAAAAALkexIoliIyE63ca+8komzg==")
                    .cookie(".ASPXAUTH","F5E5CDB11AD0DC6673421CD54DF3C7B9E91A99652EA4F5A289D9CD7C080FB3E6136B75A872055EB6ED68B8BD38C9D1753B8AF4B80A7F67CE31326A6DC4CDB7393EE18E7FB8A1E54E291A0FC9BFC56DA2AB7BCB36B5116D4CC8BBBD28127BB9DDD439680651647D719B3A76112A6BF44C87AB9E19A92D078F2D6FD24835E4DC73369599CB49BD5A45F3D4B87AC2C77B0A95289DFF4F2BC9BEB283A1FEE595A00A0C5987E08D71B99448C193C243414603AC4E8EF0682ACFDE19983B7F6289D96BAFDA8C43F23B6420568F47EA012AEF3C91536899D2D2D566AC6B8D93493D9CEFA52FF69677A1BCE4F29C981D8166323560881E4C8FCE0A54A9B34A3B133603BEF0EABE6E5F5E5549F51DB1ED6E860CC0E77DB3E2C67C5EFC0F8AEA8F8683141D2800B30F3BB4EC2A204DFE95D8F44BBB")
                    .cookie("CookieConsent","{stamp:%27SqXSt1E7eHBPJW7AecHOv9p23YL0bJSSvBY6nkzl+rmW3Vw/tPuw5Q==%27%2Cnecessary:true%2Cpreferences:true%2Cstatistics:false%2Cmarketing:false%2Cver:1%2Cutc:1625054610721%2Cregion:%27pl%27}")
                    .cookie("gtm_eu.investorType","individual investor")
                    .cookie("gtm_eu.investorCountry","en-gb")
                    .cookie("_lo_uid","208423-1625041926667-26a6a66f79e02021")
                    .cookie("__lotr","https%3A%2F%2Fwww.google.com%2F")
                    .cookie("visid_incap_1092903","CGRfQzWTTQmuJW+9ErKJOwQs3GAAAAAAQUIPAAAAAAAiV0ueLRXaenHAjLH9tlVp")
                    .cookie("_lo_v","3")
                    .cookie("wti_terms","wti_culture=en-gb&wti_role=48F2857F-8CDF-4203-B64E-0ACFD5EBAFD2&wti_terms_accepted=true")
                    .cookie("nlbi_1092903","qGVISwNA/kSd94u8r9nqIwAAAADgo1brhbD1tcjANZVQpmmo")
                    .cookie("_lorid","208423-1625054271508-18433b8297b52d07")
                    .cookie("__lotl","https%3A%2F%2Fwww.wisdomtree.eu%2Fen-gb%2Fetfs%2Fthematic%2Fwcld---wisdomtree-cloud-computing-ucits-etf---usd-acc")
                    .cookie("ASP.NET_SessionId","zxhia4shpibfaoc2dfz4halm")
                    .cookie("production cd eu corporate site#lang","en-GB")
                  */  .get();
            System.out.println(doc);
            System.out.println(doc.title());
           // String pricePath = doc.select("#fund-nav > div > table > tbody > tr.strong > td:nth-child(2) > span").toString();
           // System.out.println(pricePath);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public String getTicket() {
        return null;
    }

    @Override
    public double getCurrentPricePLN() {
        return 0;
    }

    @Override
    public double getPe() {
        return 0;
    }

    @Override
    public double getPb() {
        return 0;
    }
}
