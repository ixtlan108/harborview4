package harborview.stockmarket.service;

import harborview.stockmarket.stock.StockPrice;
import harborview.stockmarket.stock.StockTicker;
import harborview.stockmarket.stockoption.StockOptionPurchase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockMarketAdapter implements StockMarketService {

    private final Logger logger = LogManager.getLogger(StockMarketAdapter.class);

    @Override
    public List<StockPrice> getStockPrices(StockTicker ticker, LocalDate fromDx) {
        return List.of();
    }

    @Override
    public List<StockOptionPurchase> activePurchasesWithCritters(int purchaseType) {
        return List.of();
    }

}
