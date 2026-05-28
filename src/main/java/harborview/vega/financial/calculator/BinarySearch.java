package harborview.vega.financial.calculator;

import org.springframework.stereotype.Component;
import harborview.vega.exception.BinarySearchException;

@Component
public class BinarySearch {
    private final int maxIterations;

    BinarySearch() {
        maxIterations = 25;
    }
    BinarySearch(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public double find(BinarySearchAble fn, double startValue, double target, double tolerance) {
        BinarySearchBounds bounds = findBounds(fn, startValue, target);
        if (isWithinTolerance(bounds.getEndResult(), target, tolerance)) {
            return bounds.getEnd();
        }
        if (bounds.isIncr()) {
            return binarySearchIncr(fn,bounds, target, tolerance);
        }
        else {
            return binarySearchDecr(fn,bounds, target, tolerance);
        }
    }

    //region Binary Search
    private double binarySearchIncr(BinarySearchAble fn, BinarySearchBounds bounds, double target, double tolerance) {
        double lo = bounds.getStart();
        double hi = bounds.getEnd();
        int counter = 0;

        double mid = (hi+lo)/2.0;
        double result = fn.apply(mid);
        while (!isWithinTolerance(result,target,tolerance)) {
            if (counter >= maxIterations) {
                throw new BinarySearchException(String.format("Max iterations (%d) reached. Last attempted value/result: %.4f/%.4f",
                        maxIterations, mid, result));
            }
            if (result < target) {
                lo = mid;
            }
            else {
                hi = mid;
            }
            mid = (hi+lo)/2.0;
            result = fn.apply(mid);
            ++counter;
        }
        return mid;
    }


    private double binarySearchDecr(BinarySearchAble fn, BinarySearchBounds bounds, double target, double tolerance) {
        double lo = bounds.getStart();
        double hi = bounds.getEnd();
        int counter = 0;

        double mid = (hi+lo)/2.0;
        double result = fn.apply(mid);
        while (!isWithinTolerance(result,target,tolerance)) {
            if (counter >= maxIterations) {
                throw new BinarySearchException(String.format("Max iterations (%d) reached", maxIterations));
            }
            if (result > target) {
                lo = mid;
            }
            else {
                hi = mid;
            }
            mid = (hi+lo)/2.0;
            result = fn.apply(mid);
            ++counter;
        }
        return mid;
    }
    //endregion Binary Search

    //region Find Bounds
    BinarySearchBounds findBounds(BinarySearchAble fn, double startValue, double target) {
        double r = fn.apply(startValue);
        double r1 = fn.apply(startValue+0.1);
        boolean isIncr = r1 > r;
        return isIncr ? findBoundsIncr(fn,startValue,target, r) :
                findBoundsDec(fn,startValue,target, r);
    }
    private BinarySearchBounds findBoundsIncr(BinarySearchAble fn, double startValue, double target, double startResult) {
        if (startResult >= target) {
            return new BinarySearchBounds(0.0, startValue, startResult, true);
        }
        double s = startValue * 2.0;
        while (true) {
            if (s == Double.POSITIVE_INFINITY) {
                throw new BinarySearchException("Infinity");
            }
            double r2 = fn.apply(s);
            if (r2 >= target) {
                return new BinarySearchBounds(startValue, s, r2, true);
            }
            s = s*2.0;
        }
    }
    private BinarySearchBounds findBoundsDec(BinarySearchAble fn, double startValue, double target, double startResult) {
        if (startResult <= target) {
            return new BinarySearchBounds(0.0, startValue, startResult, false);
        }
        double s = startValue * 2.0;
        while (true) {
            if (s == Double.POSITIVE_INFINITY) {
                throw new BinarySearchException("Infinity");
            }
            double r2 = fn.apply(s);
            if (r2 <= target) {
                return new BinarySearchBounds(startValue, s, r2, false);
            }
            s = s*2.0;
        }
    }
    //endregion

    private boolean isWithinTolerance(double value, double target, double tolerance) {
        return (Math.abs(value-target)) < tolerance;
    }
}

