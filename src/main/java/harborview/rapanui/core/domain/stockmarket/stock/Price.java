package harborview.rapanui.core.domain.stockmarket.stock;

import java.time.LocalDateTime;

public record Price(double value, LocalDateTime timestamp) {
}
