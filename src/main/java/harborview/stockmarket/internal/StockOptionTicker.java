package harborview.stockmarket.internal;

import java.util.Objects;

public record StockOptionTicker(String ticker) {
    public StockOptionTicker {
        Objects.requireNonNull(ticker);
    }
}
