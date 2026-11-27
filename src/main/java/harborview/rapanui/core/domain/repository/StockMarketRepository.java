package harborview.rapanui.core.domain.repository;

import harborview.rapanui.core.domain.stockmarket.stockoption.StockOptionTicker;
import harborview.rapanui.kernel.dto.euronext.OptionPriceDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;

public interface StockMarketRepository {
    Either<Error, OptionPriceDTO> findOption(StockOptionTicker ticker);
}
