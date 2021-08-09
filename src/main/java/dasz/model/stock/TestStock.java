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

        String url = "https://www.us-api.morningstar.com/sal/sal-service/etf/process/stockStyle/v2/F00000J5JJ/data?languageId=en-GB&locale=en-GB&clientId=MDC_intl&benchmarkId=category&component=sal-components-mip-measures&version=3.40.1";
      //  String url = "https://www.morningstar.co.uk/uk/etf/snapshot/snapshot.aspx?id=0P0000SDK9&tab=3";
      //  String url = "http://www.w3.org/1999/xhtml";

        String data = null;
        try {
            data = Jsoup.connect(url)
                    .data("x-amz-apigw-id", "DlsMWGR7oAMFkTQ=")
                    .data("x-amz-cf-id", "s2-Hxo6o4q3TSkUf8fNIuoJFC59guedel1btWtOfacf766ofzjx&jA==")
                    .data("x-amz-cf-pop","HEL50-C2")
                    .data("x-amzn-requestid", "193527fe-f131-4309-82b0-42bbed58b45b")
                    .data("x-cache","Missing from cloudfront")
                    .method(Connection.Method.POST)
                    .ignoreContentType(true).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // Connection.Response response = Jsoup.connect(url).execute();
       // Map<String, String> responseCookies = response.cookies();
       // System.out.println("Response cookies received: " + responseCookies.size());
       // System.out.println(responseCookies);
       // responseCookies.put("ad-profile","%7b%22AudienceType%22%3a2%2c%22UserType%22%3a0%2c%22PortofolioCreated%22%3a0%2c%22IsForObsr%22%3afalse%2c%22NeedRefresh%22%3atrue%2c%22NeedPopupAudienceBackfill%22%3afalse%2c%22EnableInvestmentInUK%22%3a-1%7d");
       // System.out.println(responseCookies);

        /*Document doc = Jsoup.connect(url)
                .cookie("ad-profile","%7b%22AudienceType%22%3a2%2c%22UserType%22%3a0%2c%22PortofolioCreated%22%3a0%2c%22IsForObsr%22%3afalse%2c%22NeedRefresh%22%3atrue%2c%22NeedPopupAudienceBackfill%22%3afalse%2c%22EnableInvestmentInUK%22%3a-1%7d")
                .get();
        System.out.println(doc.title());
        System.out.println(doc.select("body > div").toString());
        */

      // System.out.println(doc.select("#overviewQuickstatsDiv > table > tbody > tr:nth-child(2) > td.line.text").toString());
      //  System.out.println(doc.select("http://www.w3.org/1999/xhtml > body > div > sal-components-pillar-cards-process > div > div:nth-child(2) > div > div:nth-child(3) > div:nth-child(2) > div > sal-components-mip-style-measures > div > div:nth-child(3) > div > div:nth-child(1) > sal-components-mip-measures > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2)").toString());
        //System.out.println(doc);

        System.out.println(data);

    }

    public static void main(String[] args) throws IOException {
        TestStock testStock = new TestStock("aaa", "bbb");
    }


}
