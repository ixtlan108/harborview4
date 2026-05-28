package harborview.vega.financial.calculator;

public class SpotFinder implements BinarySearchAble {
    private final OptionPricing optionPricing;
    private final double x;
    private final double t;
    private final double sigma;

    public SpotFinder(OptionPricing optionPricing, double x, double t, double sigma) {
        this.optionPricing = optionPricing;
        this.x = x;
        this.t = t;
        this.sigma = sigma;
    }

    @Override
    public double apply(double value) {
        return optionPricing.apply(value,x,t,sigma);
    }
}
