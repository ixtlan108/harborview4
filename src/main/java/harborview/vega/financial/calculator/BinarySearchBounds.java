package harborview.vega.financial.calculator;

public class BinarySearchBounds {
    private final double start;
    private final double end;
    private final double endResult;
    private final boolean isIncr;

    public BinarySearchBounds(double start, double end, double endResult, boolean isIncr) {
        this.start = start;
        this.end = end;
        this.endResult = endResult;
        this.isIncr = isIncr;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public double getEndResult() {
        return endResult;
    }

    public boolean isIncr() {
        return isIncr;
    }
}
