package harborview.nordnet.vega.financial;

public enum StockOptionType {
    CALL(1, "c"),
    PUT(2, "p"),
    UNDEF(3, "undef");

    private final String value;
    private final int index;

    StockOptionType(int index, String value) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return value;
    }
    public int getIndex() {
        return index;
    }
}
