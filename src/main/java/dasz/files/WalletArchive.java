package dasz.files;

import dasz.model.currency.EurToPln;
import dasz.model.currency.UsdToPln;
import dasz.model.stock.Stock;
import dasz.model.wallet.Wallet;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class WalletArchive {

    public static void createWalletArchive(Wallet wallet){
        String fileToWrite = "src/main/java/dasz/files/walletArchive/wallet "+ LocalDate.now() +".txt";
        try {
            FileWriter myWriter = new FileWriter(fileToWrite);
            for (Stock s: wallet.getStocks()) {
                myWriter.write(s.toString()+"\n");
                saveStockArchive(s);
            }
            myWriter.write("$ -> zł = " + UsdToPln.get()+"\n");
            myWriter.write("€ -> zł = " + EurToPln.get()+"\n");
            myWriter.write(wallet.getWalletTotal());
            myWriter.close();
            System.out.println("Successfully wrote to the files.");
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void saveStockArchive(Stock stock){
        String fileToWrite = "src/main/java/dasz/files/stockArchive/"+stock.getTicket()+".txt";
        try {
            FileWriter myWriter = new FileWriter(fileToWrite, true);
            myWriter.write(stock.toString()+" "+LocalDate.now()+"\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
