package harborview.vega.financial.calculator.binomialtree;

import org.springframework.stereotype.Component;
import harborview.vega.StockOptionPrice;
import harborview.vega.StockOptionType;
import harborview.vega.OptionCalculator;

@Component("binomialTree")
public class BinomialTreeCalculator implements OptionCalculator {

    double daysInAYear = 365.0;

    @Override
    public double delta(StockOptionPrice d) {
        return 0;
    }

    @Override
    public double spread(StockOptionPrice d) {
        return 0;
    }

    @Override
    public double breakEven(StockOptionPrice d) {
        return 0;
    }

    @Override
    public double stockPriceFor(double optionPrice, StockOptionPrice o) {
        return 0;
    }

    @Override
    public double stockPriceFor2(StockOptionType optionType, double optionPrice, double x, long days, double iv, double stockPrice) {
        return 0;
    }

    @Override
    public double iv(StockOptionPrice d, int priceType) {
        return 0;
    }

    @Override
    public double ivCall(double spot, double strike, double yearsExpiry, double optionPrice) {
        return 0;
    }

    @Override
    public double ivPut(double spot, double strike, double yearsExpiry, double optionPrice) {
        return 0;
    }

    @Override
    public double callPrice(double spot, double strike, double yearsExpiry, double sigma) {
        return 0;
    }

    @Override
    public double callPrice2(double spot, double strike, long days, double sigma) {
        return 0;
    }

    @Override
    public double putPrice(double spot, double strike, double yearsExpiry, double sigma) {
        return 0;
    }

    @Override
    public double putPrice2(double spot, double strike, long days, double sigma) {
        return 0;
    }
}
