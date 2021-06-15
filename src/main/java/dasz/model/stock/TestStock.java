package dasz.model.stock;


import org.jsoup.Connection;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestStock {
    private String ticket;
    private String linkSuffix;

    public TestStock(String ticket, String linkSuffix) throws IOException {
        this.ticket = ticket;
        this.linkSuffix = linkSuffix;

        String url = "https://www.morningstar.co.uk/uk/etf/snapshot/snapshot.aspx?id=0P0000SDK9&tab=3";
      //  String url = "http://www.w3.org/1999/xhtml";

       // Connection.Response response = Jsoup.connect(url).execute();
       // Map<String, String> responseCookies = response.cookies();
       // System.out.println("Response cookies received: " + responseCookies.size());
       // System.out.println(responseCookies);
       // responseCookies.put("ad-profile","%7b%22AudienceType%22%3a2%2c%22UserType%22%3a0%2c%22PortofolioCreated%22%3a0%2c%22IsForObsr%22%3afalse%2c%22NeedRefresh%22%3atrue%2c%22NeedPopupAudienceBackfill%22%3afalse%2c%22EnableInvestmentInUK%22%3a-1%7d");
       // System.out.println(responseCookies);

        Document doc = Jsoup.connect(url)
                .header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                .maxBodySize(0)
                .timeout(600000)
                .get();
     //           .cookie("ad-profile","%7b%22AudienceType%22%3a2%2c%22UserType%22%3a0%2c%22PortofolioCreated%22%3a0%2c%22IsForObsr%22%3afalse%2c%22NeedRefresh%22%3atrue%2c%22NeedPopupAudienceBackfill%22%3afalse%2c%22EnableInvestmentInUK%22%3a-1%7d")

        System.out.println(doc.title());
        System.out.println(doc.select("#snapshotTitleDiv > table > tbody > tr > td > div > h1").toString());
        System.out.println(doc.select("#overviewQuickstatsDiv > table > tbody > tr:nth-child(2) > td.line.text").toString());
        System.out.println(doc.select("http://www.w3.org/1999/xhtml > body > div > sal-components-pillar-cards-process > div > div:nth-child(2) > div > div:nth-child(3) > div:nth-child(2) > div > sal-components-mip-style-measures > div > div:nth-child(3) > div > div:nth-child(1) > sal-components-mip-measures > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2)").toString());
        //System.out.println(doc);



    }

    public static void main(String[] args) throws IOException {
        TestStock testStock = new TestStock("aaa", "bbb");
    }


}
