package harborview.stockmarket.service;

import harborview.stockmarket.mybatis.CritterMapper;
import harborview.stockmarket.stock.StockPrice;
import harborview.stockmarket.stock.StockTicker;
import harborview.stockmarket.stockoption.StockOptionPurchase;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockMarketAdapter implements StockMarketService {

    protected final SqlSession session;

    private final Logger logger = LogManager.getLogger(StockMarketAdapter.class);

    public StockMarketAdapter(SqlSession session) {
        this.session = session;
    }

    @Override
    public List<StockPrice> getStockPrices(StockTicker ticker, LocalDate fromDx) {
        return List.of();
    }

    @Override
    public List<StockOptionPurchase> activePurchasesWithCritters(int purchaseType) {
        var mapper = session.getMapper(CritterMapper.class);
        return mapper.activePurchasesWithCritters(purchaseType);
    }

}
