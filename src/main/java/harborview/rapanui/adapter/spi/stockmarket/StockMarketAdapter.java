package harborview.rapanui.adapter.spi.stockmarket;

import harborview.rapanui.core.domain.repository.StockMarketRepository;
import harborview.rapanui.core.domain.stockmarket.stockoption.StockOptionTicker;
import harborview.rapanui.core.spi.euronext.EuroNextPort;
import harborview.rapanui.kernel.dto.euronext.OptionPriceDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;
import org.springframework.stereotype.Component;

@Component
public class StockMarketAdapter implements StockMarketRepository {
    private final EuroNextPort euroNextPort;

    public StockMarketAdapter (EuroNextPort euroNextPort) {
        this.euroNextPort = euroNextPort;
    }

    @Override
    public Either<Error, OptionPriceDTO> findOption(StockOptionTicker ticker) {
        return null;
    }
}
