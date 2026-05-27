package harborview.nordnet.vega.financial;

public interface StockOption {
    public static int BUY = 1;
    public static int SELL = 2;

    //public static enum LifeCycle { FROM_HTML, SAVED_TO_DATABASE, FROM_DATABASE };

    StockOptionType getOpType();
    double getX();
    long getDays();
}
