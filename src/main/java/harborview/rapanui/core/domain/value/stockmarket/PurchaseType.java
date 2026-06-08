package harborview.rapanui.core.domain.value.stockmarket;

public record PurchaseType(int value) {
    public PurchaseType {
        if (value != 4 && value != 11) {
            throw new IllegalArgumentException("Purchase type must be 4 or 11");
        }
    }
}
