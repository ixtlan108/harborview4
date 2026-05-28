package harborview.vega.financial.calculator;

public interface OptionPricing extends BinarySearchAble {
    double apply(double spot, double x, double t, double sigma);
}
