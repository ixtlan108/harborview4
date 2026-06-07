package harborview.stockmarket.mybatis;

import harborview.stockmarket.stock.Stock;
import harborview.stockmarket.stock.StockPrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

@Mapper
public interface StockMapper {

    List<Stock> selectStocks();

    List<StockPrice> selectStockPrices(@Param("tickerId") int tickerId,
                                       @Param("fromDx") Date fromDx);

}
