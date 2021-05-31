package dasz;

import dasz.files.WalletArchive;
import dasz.model.currency.EurToPln;
import dasz.model.currency.GbpToPln;
import dasz.model.currency.UsdToPln;
import dasz.model.wallet.EtfListAnalyze;
import dasz.model.wallet.Wallet;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Wallet wallet = new Wallet(new File("src/main/java/dasz/files/walletCurrent/wallet.txt"));
        wallet.printWallet();
        System.out.println("$ -> zł = " + UsdToPln.get());
        System.out.println("€ -> zł = " + EurToPln.get());
        System.out.println("£ -> zł = " + GbpToPln.get());
        WalletArchive.createWalletArchive(wallet);
    }

    private static class Analyze{
        public static void main(String[] args) {
            EtfListAnalyze etfListAnalyze = new EtfListAnalyze(new File("src/main/java/dasz/files/walletCurrent/etfList.txt"));
            System.err.println("Sorted:");
            etfListAnalyze.printAll();
        }
    }

    private static class Test{
        public static void main(String[] args) {
            EtfListAnalyze etfListAnalyze = new EtfListAnalyze(new File("src/main/java/dasz/files/walletCurrent/test.txt"));

            etfListAnalyze.printAll();
        }
    }
}
