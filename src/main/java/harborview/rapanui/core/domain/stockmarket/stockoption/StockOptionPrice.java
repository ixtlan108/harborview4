package harborview.rapanui.core.domain.stockmarket.stockoption;

import java.time.LocalDateTime;

public record StockOptionPrice(double bid, double ask, LocalDateTime timestamp) {
}
