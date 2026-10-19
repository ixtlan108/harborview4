package harborview.shared.dto;

public enum Month {
    JAN(1, "Jan"),
    FEB(2, "Feb"),
    MARCH(3, "Mar"),
    APRIL(4, "Apr"),
    MAY(5, "May"),
    JUNE(6, "Jun"),
    JULY(7, "Jul"),
    AUG(8, "Aug"),
    SEPT(9, "Sep"),
    OCT(10, "Oct"),
    NOV(11, "Nov"),
    DEC(12, "Dec");

    private final int index;
    private final String value;

    Month (int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static Month fromString(String val) {
        return switch (val) {
            case "Jan" -> JAN;
            case "Feb" -> FEB;
            case "Mar" -> MARCH;
            case "Apr" -> APRIL;
            case "May" -> MAY;
            case "Jun" -> JUNE;
            case "Jul" -> JULY;
            case "Aug" -> AUG;
            case "Sep" -> SEPT;
            case "Oct" -> OCT;
            case "Nov" -> NOV;
            case "Dec" -> DEC;
            default -> throw new IllegalArgumentException("Unknown month: " + val);
        };
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}
