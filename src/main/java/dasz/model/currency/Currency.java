package dasz.model.currency;

public enum Currency {
    USD('$',UsdToPln.get()),
    EUR('€',EurToPln.get()),
    GBP('£',GbpToPln.get());

    private char symbol;
    private double value;

    Currency(char symbol, double value) {
        this.symbol = symbol;
        this.value = value;
    }

    public char getSymbol() {
        return symbol;
    }

    public double getValue() {
        return value;
    }
}
