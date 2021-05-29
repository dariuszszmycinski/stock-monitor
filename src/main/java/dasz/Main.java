package dasz;

import dasz.files.WalletArchive;
import dasz.model.currency.EurToPln;
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
        WalletArchive.createWalletArchive(wallet);
    }

    private static class Analyze{
        public static void main(String[] args) {
            EtfListAnalyze etfListAnalyze = new EtfListAnalyze(new File("src/main/java/dasz/files/walletCurrent/etfList.txt"));
            etfListAnalyze.printAll();
        }
    }
}
