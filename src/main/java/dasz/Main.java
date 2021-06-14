package dasz;

import dasz.files.WalletArchive;
import dasz.model.currency.EurToPln;
import dasz.model.currency.GbpToPln;
import dasz.model.currency.UsdToPln;
import dasz.model.stock.Ishares;
import dasz.model.stock.Spdr;
import dasz.model.stock.Vanguard;
import dasz.model.stock.Xtrackers;
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
            etfListAnalyze.printAll();
        }
    }

    private static class Test{
        public static void main(String[] args) {
            System.out.println(new Vanguard("FRA:VFEA VANGUARD FTSE EMERGING MARKETS UCITS ETF USD _Globalny (EM)_Akcje",9678));
        }
    }
}
