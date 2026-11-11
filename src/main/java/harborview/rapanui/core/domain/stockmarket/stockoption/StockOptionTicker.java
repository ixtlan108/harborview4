package harborview.rapanui.core.domain.stockmarket.stockoption;

import harborview.shared.functional.Either;
import harborview.rapanui.kernel.error.Error;

public class StockOptionTicker {

    private StockOptionTicker() {
    }

    public static Either<Error, StockOptionTicker> create(String ticker) {
        return Either.right(new StockOptionTicker());
    }
}
