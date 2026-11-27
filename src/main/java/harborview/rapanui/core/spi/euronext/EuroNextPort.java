package harborview.rapanui.core.spi.euronext;

import harborview.rapanui.core.domain.stockmarket.stock.Ticker;
import harborview.rapanui.kernel.dto.euronext.OptionPriceDTO;
import harborview.rapanui.kernel.dto.euronext.OptionPricesDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;

import java.util.List;

public interface EuroNextPort {
    Either<Error, OptionPricesDTO> findAllOptions(Ticker ticker);
}
