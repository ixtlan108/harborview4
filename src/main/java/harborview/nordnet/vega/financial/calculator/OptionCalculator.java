/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package harborview.nordnet.vega.financial.calculator;

import harborview.nordnet.vega.financial.StockOptionPrice;
import harborview.nordnet.vega.financial.StockOptionType;

public interface OptionCalculator {

    double delta(StockOptionPrice d);
    double spread(StockOptionPrice d);
    double breakEven(StockOptionPrice d);
    double stockPriceFor(double optionPrice, StockOptionPrice o);
    double stockPriceFor2(StockOptionType optionType,
                          double optionPrice,
                          double x,
                          long days,
                          double iv,
                          double stockPrice);
    double iv(StockOptionPrice d, int priceType);

    double ivCall(double spot, double strike, double yearsExpiry, double optionPrice);
    double ivPut(double spot, double strike, double yearsExpiry, double optionPrice);
    double callPrice(double spot, double strike, double yearsExpiry, double sigma);
    double callPrice2(double spot, double strike, long days, double sigma);
    double putPrice(double spot, double strike, double yearsExpiry, double sigma);
    double putPrice2(double spot, double strike, long days, double sigma);
}
