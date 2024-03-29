package dasz;


import dasz.files.WalletArchive;
import dasz.model.currency.EurToPln;
import dasz.model.currency.GbpToPln;
import dasz.model.currency.UsdToPln;
import dasz.model.stock.*;
import dasz.model.wallet.EtfListAnalyze;
import dasz.model.wallet.Wallet;

import java.io.File;

//project abadoned because of constant source sides changes, besides of lack of $

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
            etfListAnalyze.printAndSaveAll();
        }
    }

    private static class Test{
        public static void main(String[] args) {
            System.out.println(new Stooq("Amica","amc",1));
            //System.out.println(new Stooq("Asbis","asb",2));
            //System.out.println(new Stooq("GPW","gpw",3));
            //System.out.println(new Stooq("Kernel","ker",2));
            //System.out.println(new Stooq("XTB","xtb",2));
        }
    }
}
