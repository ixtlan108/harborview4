package harborview.stockmarket.service;

import harborview.stockmarket.mybatis.CritterMapper;
import harborview.stockmarket.mybatis.StockMapper;
import harborview.stockmarket.stock.Stock;
import harborview.stockmarket.stock.StockPrice;
import harborview.stockmarket.stock.StockTicker;
import harborview.stockmarket.stockoption.StockOptionPurchase;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class StockMarketAdapter implements StockMarketService {

    protected final SqlSession session;
    private final RedisAdapter redisAdapter;
    private final Date fromDate;

    private final Logger logger = LogManager.getLogger(StockMarketAdapter.class);

    private List<Stock> stocks;

    public StockMarketAdapter(SqlSession session, RedisAdapter redisAdapter,
                              @Value("${adapter.stockmarket.from-date}") Date fromDate) {
        this.session = session;
        this.redisAdapter = redisAdapter;
        this.fromDate = fromDate;
    }

    private void populateStocksIfEmtpy() {
        if (stocks == null) {
            var mapper = session.getMapper(StockMapper.class);
            stocks = mapper.selectStocks();
        }
    }

    @Override
    public List<Stock> getStocks() {
        populateStocksIfEmtpy();
        return stocks;
    }

    @Override
    public StockPrice getSpot(StockTicker ticker) {
        return redisAdapter.getSpot(ticker);
    }

    @Override
    public List<StockPrice> getStockPrices(StockTicker ticker, LocalDate fromDx) {
        var mapper = session.getMapper(StockMapper.class);
        if (fromDx == null) {
            return mapper.selectStockPrices(ticker.oid(), fromDate);
        }
        else {
            return mapper.selectStockPrices(ticker.oid(), Date.valueOf(fromDx));
        }
    }

    @Override
    public List<StockOptionPurchase> activePurchasesWithCritters(int purchaseType) {
        var mapper = session.getMapper(CritterMapper.class);
        return mapper.activePurchasesWithCritters(purchaseType);
    }

}
