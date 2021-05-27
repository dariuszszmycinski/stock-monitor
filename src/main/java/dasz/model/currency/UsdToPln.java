package dasz.model.currency;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class UsdToPln {
    public static double get(){
        try {
            Document doc = Jsoup.connect("https://bossafx.pl/oferta/instrumenty/USDPLN.").get();
            double usdToPln = Double.parseDouble(doc.select("#symbol-mid").toString().replace("<div id=\"symbol-mid\" class=\"instrument-overflow-detail-value\">", "").replace("</div>", ""));
            return usdToPln;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
