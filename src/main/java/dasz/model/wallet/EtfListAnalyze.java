package dasz.model.wallet;

import dasz.files.WalletArchive;
import dasz.model.stock.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class EtfListAnalyze {
    private ArrayList<Stock> stocks = new ArrayList<>();
    private File file;

    public EtfListAnalyze(File file) {
        this.file = file;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                switch (line[0]) {
                    case "Ishares":
                        stocks.add(new Ishares(line[1], Integer.parseInt(line[2])));
                        break;
                    case "Beta40Tr":
                        stocks.add(new Beta40Tr());
                        break;
                    case "JustEtf":
                        stocks.add(new JustEtf(line[1], line[2]));
                        break;
                    case "Xtrackers":
                        Xtrackers xtrackers;
                        if (line.length==4){
                            xtrackers = new Xtrackers(line[1], line[2], line[3]);
                        }else {
                            xtrackers = new Xtrackers(line[1], line[2]);
                        }
                        stocks.add(xtrackers);
                        break;
                    case "SPDR":
                        stocks.add(new Spdr(line[1], line[2]));
                        break;
                    case "Vanguard":
                        stocks.add(new Vanguard(line[1], Integer.parseInt(line[2])));
                        break;
                    default:
                        System.err.println(line[0]+"Wrong stock type in etfList.txt file.");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void printAndSaveAll() {
        stocks.sort(Comparator.comparing(Stock::getPe));
        System.out.println("-----Sorted-----");
        WalletArchive.saveEtfListAnalyze(stocks);
        for (Stock s : stocks) {
            System.out.println(s);
        }
    }

}
