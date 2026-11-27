package harborview.rapanui.core.application.handler.euronext;

import harborview.rapanui.core.domain.repository.StockMarketRepository;
import harborview.rapanui.core.domain.stockmarket.stock.Ticker;
import harborview.rapanui.core.domain.stockmarket.stockoption.StockOptionTicker;
import harborview.rapanui.kernel.dto.euronext.OptionPriceDTO;
import harborview.rapanui.kernel.dto.euronext.OptionPricesDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.annotation.ApplicationService;
import harborview.shared.functional.Either;
import org.springframework.stereotype.Component;

import java.util.List;

@ApplicationService
@Component
public class FetchOptionsHandler {

    private final StockMarketRepository repository;

    public FetchOptionsHandler(StockMarketRepository repository) {
        this.repository = repository;
    }

    public Either<Error, OptionPricesDTO> fetchOptions(String ticker) {
        var stockTicker = Ticker.create(ticker);

        if (stockTicker.isLeft()) {
            return Either.left(stockTicker.getLeft());
        }
        //return euroNextPort.findAllOptions(stockTicker.getRight());
        return null;
    }

    public Either<Error, OptionPriceDTO> fetchOption(String ticker) {

        /*
        var optionTicker = StockOptionTicker.create(ticker);

        if (optionTicker.isLeft()) {
            return Either.left(optionTicker.getLeft());
        }

        return euroNextPort.findOption(optionTicker.getRight());

         */
        return null;
    }

}
