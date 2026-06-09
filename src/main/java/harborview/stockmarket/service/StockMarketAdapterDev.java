package harborview.stockmarket.service;

import harborview.stockmarket.mybatis.CritterMapper;
import harborview.stockmarket.stockoption.StockOptionPurchase;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@Profile("dev")
public class StockMarketAdapterDev extends StockMarketAdapter {

    private final Logger logger = LogManager.getLogger(StockMarketAdapterDev.class);

    public StockMarketAdapterDev(SqlSession session, RedisAdapter redisAdapter,
                                 @Value("${adapter.stockmarket.from-date}") Date fromDate) {
        super(session,redisAdapter,fromDate);
    }

    @Override
    public List<StockOptionPurchase> activePurchasesWithCritters(int purchaseType) {
        var mapper = session.getMapper(CritterMapper.class);
        return mapper.activePurchasesWithCrittersDev(purchaseType);
    }

}
