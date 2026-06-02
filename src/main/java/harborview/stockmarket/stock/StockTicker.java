package harborview.stockmarket.stock;

import harborview.stockmarket.service.StockMarketAdapterUtil;

import java.util.Objects;

public record StockTicker(String ticker, int oid) {
    public StockTicker(String ticker) {
        this(ticker, StockMarketAdapterUtil.mapString(ticker));
    }
    public StockTicker(int oid) {
        this(StockMarketAdapterUtil.mapOid(oid), oid);
    }
    public StockTicker(String ticker, int oid) {
        this.ticker = ticker;
        this.oid = oid;
        Objects.requireNonNull(ticker);
    }
}
