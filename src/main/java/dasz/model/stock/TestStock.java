package dasz.model.stock;

import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;

public class TestStock {
    private String ticket;
    private String linkSuffix;

    public TestStock(String ticket, String linkSuffix) {
        this.ticket = ticket;
        this.linkSuffix = linkSuffix;

        String url = "https://www.morningstar.co.uk/uk/etf/snapshot/snapshot.aspx?id=0P0000SDKC&tab=3&InvestmentType=FE";


        try {
            String data = Jsoup.connect(url).ignoreContentType(true).execute().body();
            System.out.println(data);
            Document doc = Jsoup.connect(url).get();
            System.out.println(doc.title() );
            String aaa = doc.select("body > div > sal-components-pillar-cards-process > div > div:nth-child(2) > div > div:nth-child(3) > div:nth-child(2) > div > sal-components-mip-style-measures > div > div:nth-child(3) > div > div:nth-child(1) > sal-components-mip-measures > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div > table > tbody > tr:nth-child(1)").toString();
            System.out.println(aaa);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        TestStock testStock = new TestStock("aaa", "bbb");
    }
}
