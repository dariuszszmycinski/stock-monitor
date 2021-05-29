package dasz.model.stock;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class WalletStock {
    private Stock stock;
    private LocalDate bought;
    private double basePricePLN;
    private int quantity;
    private double total;
    private double totalGrowth;
    private double annualGrowth;
    private double profit;

    public WalletStock(Stock stock, int quantity, double basePricePLN ,LocalDate bought) {
        this.stock = stock;
        this.bought = bought;
        this.basePricePLN = basePricePLN;
        this.quantity = quantity;
        this.total = (double)Math.round((quantity * stock.getCurrentPricePLN())*100)/100;
        this.totalGrowth = (double) Math.round(((total-basePricePLN)/basePricePLN)*10000)/100;
        this.annualGrowth = (double) Math.round((totalGrowth*365/ ChronoUnit.DAYS.between(bought, LocalDate.now()))*100)/100;
        this.profit = (double) Math.round((total-basePricePLN)*100)/100;
    }

    public double getTotal() {
        return total;
    }

    public String getTicket(){
        return stock.getTicket();
    }

    @Override
    public String toString() {
        return stock.toString()+" total= " + getTotal() + "zł, totalGrowth= "+totalGrowth+"% "+"annualGrowth= "+annualGrowth+"%"+" profit= "+profit+"zł";
    }
}
