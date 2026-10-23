package harborview.rapanui.core.application.handler.euronext;

import harborview.rapanui.core.domain.stockmarket.stock.Ticker;
import harborview.rapanui.core.spi.euronext.EuroNextPort;
import harborview.rapanui.kernel.dto.euronext.OptionDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.annotation.ApplicationService;
import harborview.shared.functional.Either;
import org.springframework.stereotype.Component;

import java.util.List;

@ApplicationService
@Component
public class FetchOptionsHandler {
    private final EuroNextPort euroNextPort;

    public FetchOptionsHandler(EuroNextPort euroNextPort) {
        this.euroNextPort = euroNextPort;
    }
    public Either<Error,List<OptionDTO>> fetchOptions(String ticker) {
        var stockTicker = Ticker.create(ticker);

        if (stockTicker.isLeft()) {
            return Either.left(stockTicker.getLeft());
        }
        return euroNextPort.findAllOptions(stockTicker.getRight());
    }
}
