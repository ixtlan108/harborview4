package harborview.stockmarket.internal;

import harborview.stockmarket.StockPrice;
import harborview.stockmarket.StockTicker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockMarketAdapter implements StockMarketService {

    @Override
    public List<StockPrice> getStockPrices(StockTicker ticker, LocalDate fromDx) {
        return List.of();
    }

}
