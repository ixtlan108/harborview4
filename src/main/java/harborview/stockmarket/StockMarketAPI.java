package harborview.stockmarket;

import harborview.stockmarket.internal.StockMarketCore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMarketAPI {

    private final StockMarketCore core;

    public StockMarketAPI(StockMarketCore core) {
        this.core = core;
    }

    public List<StockPrice> getPrices(StockTicker ticker) {
        return core.getPrices(ticker);
    }

}
