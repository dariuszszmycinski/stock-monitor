package dasz.model.wallet;

import dasz.model.stock.Beta40Tr;
import dasz.model.stock.Ishares;
import dasz.model.stock.JustEtf;
import dasz.model.stock.Stock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wallet {
    private ArrayList<Stock> stocks = new ArrayList<>();
    private File file;

    public Wallet(File file) {
        this.file = file;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String[] line = scanner.nextLine().split(",");
                switch (line[0]){
                    case "Ishares":
                        stocks.add(new Ishares(line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3])));
                        break;
                    case "Beta40Tr":
                        stocks.add(new Beta40Tr(Integer.parseInt(line[1])));
                        break;
                    case "JustEtf":
                        stocks.add(new JustEtf(line[1], line[2], Integer.parseInt(line[3])));
                        break;
                    default:
                        System.err.println("Wrong stock type in wallet.txt file.");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void printWallet(){
        for (Stock s:stocks) {
            System.out.println(s);
        }
        System.out.println(getWalletTotal());
    }

    public String getWalletTotal(){
        double WalletTotal = 0;
        for (Stock s:stocks) {
            WalletTotal += s.getTotal();
        }
        return "Total wallet value: "+(double)Math.round((WalletTotal)*100)/100+"zł";
    }
}
