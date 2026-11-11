package harborview.shared.dto;

public enum OptionType {
    CALL(1, "Call"),
    PUT(2, "Put");

    private final int index;
    private final String value;

    OptionType(int index, String value) {
        this.index = index;
        this.value = value;
    }
    public static OptionType fromString(String val) {
        return switch (val) {
            case "Call" -> CALL;
            case "Put" -> PUT;
            default -> throw new IllegalArgumentException("Unknown option type: " + val);
        };
    }
}
