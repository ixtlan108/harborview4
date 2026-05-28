package harborview.vega.financial;

import java.util.Optional;

public interface StockOptionPrice {
    StockOption getStockOption();
    StockPrice getStockPrice();
    double getBid();
    double getAsk();
    Optional<Double> ivBid();

    /*
    StockOption getDerivative();
    StockPrice getStockPrice();
    double getDays();
    Optional<Double> getIvBuy();
    Optional<Double> getIvSell();
    double getBuy();
    double getSell();
    Optional<Double> getBreakEven();
    Optional<Double> stockPriceFor(double optionValue);
    double optionPriceFor(double stockPrice);
    int getOid();
    int getStockId();
    void setOid(int oid);
    String getTicker();
    public double getCurrentRiscOptionValue();
    public double getCurrentRisc();
    public Optional<Double> getCurrentRiscStockPrice();
    void resetRiscCalc();
     */
}
