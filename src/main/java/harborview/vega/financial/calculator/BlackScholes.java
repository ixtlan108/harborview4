package harborview.vega.financial.calculator;

import org.springframework.stereotype.Component;
import harborview.vega.financial.StockOption;
import harborview.vega.financial.StockOptionPrice;
import harborview.vega.financial.StockOptionType;

import static harborview.vega.financial.StockOptionType.CALL;
import static harborview.vega.financial.StockOptionType.PUT;


@Component("blackScholes")
public class BlackScholes implements OptionCalculator {
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
        double result = 0;
        if (o.ivBid().isPresent()) {
            double iv = o.ivBid().get();
            double x = o.getStockOption().getX();
            double t = o.getStockOption().getDays() / daysInAYear;
            OptionPricing pricing = new DefaultOptionPricing(o.getStockOption().getOpType());
            SpotFinder spotFinder = new SpotFinder(pricing,x,t,iv);
            double startValue = o.getStockPrice().getCls();
            BinarySearch binarySearch = new BinarySearch();
            result = binarySearch.find(spotFinder, startValue, optionPrice, 0.05);
        }
        return result;
    }

    @Override
    public double stockPriceFor2(StockOptionType optionType,
                                 double optionPrice,
                                 double x,
                                 long days,
                                 double iv,
                                 double startValue) {
        double result = 0;
        OptionPricing pricing = new DefaultOptionPricing(optionType);
        double t = days / daysInAYear;
        SpotFinder spotFinder = new SpotFinder(pricing,x,t,iv);
        //double startValue = stockPrice;
        BinarySearch binarySearch = new BinarySearch();
        result = binarySearch.find(spotFinder, startValue, optionPrice, 0.05);
        return result;
    }

    @Override
    public double iv(StockOptionPrice d, int priceType) {
        double price = priceType == StockOption.BUY ? d.getBid() : d.getAsk();
        double tm = d.getStockOption().getDays() / daysInAYear;
        OptionPricing fn = new DefaultOptionPricing(
                d.getStockOption().getOpType(),
                d.getStockPrice().getCls(),
                d.getStockOption().getX(),
                tm);

        BinarySearch binarySearch = new BinarySearch();
        return binarySearch.find(fn, 0.4, price, 0.05);
    }

    @Override
    public double ivCall(double spot, double strike, double yearsExpiry, double optionPrice) {
        BinarySearch binarySearch = new BinarySearch();
        OptionPricing fn = new DefaultOptionPricing(CALL, spot, strike, yearsExpiry);
        return binarySearch.find(fn, 0.4, optionPrice, 0.05);
    }

    @Override
    public double ivPut(double spot, double strike, double yearsExpiry, double optionPrice) {
        BinarySearch binarySearch = new BinarySearch();
        OptionPricing fn = new DefaultOptionPricing(PUT, spot, strike, yearsExpiry);
        return binarySearch.find(fn, 0.1, optionPrice, 0.05);
    }

    @Override
    public double callPrice(double spot, double strike, double yearsExpiry, double sigma) {
        OptionPricing optionPrice = new DefaultOptionPricing(CALL);
        return optionPrice.apply(spot, strike, yearsExpiry, sigma);
    }

    @Override
    public double callPrice2(double spot, double strike, long days, double sigma) {
        return callPrice(spot, strike, days/daysInAYear, sigma);
    }

    @Override
    public double putPrice(double spot, double strike, double yearsExpiry, double sigma) {
        OptionPricing optionPrice = new DefaultOptionPricing(PUT);
        return optionPrice.apply(spot, strike, yearsExpiry, sigma);
    }
    @Override
    public double putPrice2(double spot, double strike, long days, double sigma) {
        return putPrice(spot, strike, days/daysInAYear, sigma);
    }

}

/*
BlackScholes.java

Below is the syntax highlighted version of BlackScholes.java from §2.2 Libraries.


   Compilation:  javac BlackScholes.java MyMath.java
   Execution:    java BlackScholes s x r sigma t

   Reads in five command line inputs and calculates the option price
   according to the Black-Scholes formula.

  % java BlackScholes 23.75 15.00 0.01 0.35 0.5
  8.879159279691955                                  (actual =  9.10)

  % java BlackScholes 30.14 15.0 0.01 0.332 0.25
  15.177462481562186                                 (actual = 14.50)


  Information calculated based on closing data on Monday, June 9th 2003.

      Microsoft:   share price:             23.75
                   strike price:            15.00
                   risk-free interest rate:  1%
                  volatility:              35%          (historical estimate)
                   time until expiration:    0.5 years

       GE:         share price:             30.14
                   strike price:            15.00
                   risk-free interest rate   1%
                   volatility:              33.2%         (historical estimate)
                   time until expiration     0.25 years


  Reference:  http://www.hoadley.net/options/develtoolsvolcalc.htm



public class BlackScholes {

    // Black-Scholes formula
    public static double callPrice(double s, double x, double r, double sigma, double t) {
        double d1 = (Math.log(s/x) + (r + sigma * sigma/2) * t) / (sigma * Math.sqrt(t));
        double d2 = d1 - sigma * Math.sqrt(t);
        return s * Gaussian.cdf(d1) - x * Math.exp(-r*t) * Gaussian.cdf(d2);
    }

    // estimate by Monte Carlo simulation
    public static double call(double s, double x, double r, double sigma, double t) {
        int n = 10000;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double eps = StdRandom.gaussian();
            double price = s * Math.exp(r*t - 0.5*sigma*sigma*t + sigma*eps*Math.sqrt(t));
            double value = Math.max(price - x, 0);
            sum += value;
        }
        double mean = sum / n;

        return Math.exp(-r*t) * mean;
    }

    // estimate by Monte Carlo simulation
    public static double call2(double s, double x, double r, double sigma, double t) {
        int n = 10000;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double price = s;
            double dt = t/10000.0;
            for (double time = 0; time <= t; time += dt) {
                price += r*price*dt +sigma*price*Math.sqrt(dt)*StdRandom.gaussian();
            }
            double value = Math.max(price - x, 0);
            sum += value;
        }
        double mean = sum / n;

        return Math.exp(-r*t) * mean;
    }



    public static void main(String[] args) {
        double s     = Double.parseDouble(args[0]);
        double x     = Double.parseDouble(args[1]);
        double r     = Double.parseDouble(args[2]);
        double sigma = Double.parseDouble(args[3]);
        double t     = Double.parseDouble(args[4]);
        StdOut.println(callPrice(s, x, r, sigma, t));
        StdOut.println(call(s, x, r, sigma, t));
        StdOut.println(call2(s, x, r, sigma, t));
    }
}


Copyright © 2000–2017, Robert Sedgewick and Kevin Wayne.
        Last updated: Fri Oct 20 14:12:12 EDT 2017.
 */