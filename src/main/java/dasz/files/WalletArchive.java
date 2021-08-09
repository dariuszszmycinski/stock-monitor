package dasz.files;

import dasz.model.currency.EurToPln;
import dasz.model.currency.GbpToPln;
import dasz.model.currency.UsdToPln;
import dasz.model.stock.Stock;
import dasz.model.stock.WalletStock;
import dasz.model.wallet.Wallet;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WalletArchive {

    public static void createWalletArchive(Wallet wallet){
        String fileToWrite = "src/main/java/dasz/files/walletArchive/wallet "+ LocalDate.now() +".txt";
        try {
            FileWriter myWriter = new FileWriter(fileToWrite);
            for (WalletStock s: wallet.getStocks()) {
                myWriter.write(s.toString()+"\n");
                saveStockArchive(s);
            }
            myWriter.write("$ -> zł = " + UsdToPln.get()+"\n");
            myWriter.write("€ -> zł = " + EurToPln.get()+"\n");
            myWriter.write("£ -> zł = " + GbpToPln.get()+"\n");
            myWriter.write(wallet.getWalletTotal());
            myWriter.close();
            System.out.println("Successfully wrote to the files.");
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void saveStockArchive(WalletStock stock){
        String fileToWrite = "src/main/java/dasz/files/stockArchive/"+stock.getTicket()+".txt";
        try {
            FileWriter myWriter = new FileWriter(fileToWrite, true);
            myWriter.write(stock +" "+LocalDate.now()+"\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveEtfListAnalyze(ArrayList<Stock> stocks){
        String fileToWrite = "src/main/java/dasz/files/etfListAnalyze/analyze "+ LocalDate.now() +".txt";
        try {
            FileWriter myWriter = new FileWriter(fileToWrite, true);
            for (Stock s:stocks) {
                myWriter.write(s.getTicket().split(" ")[0]+"|"+s.getPe()+"|"+s.getPb()+"\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
