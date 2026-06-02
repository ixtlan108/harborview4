package harborview.stockmarket.stockoption;

import java.util.Objects;

public record StockOptionTicker(String ticker) {
    public StockOptionTicker {
        Objects.requireNonNull(ticker);
    }
}
