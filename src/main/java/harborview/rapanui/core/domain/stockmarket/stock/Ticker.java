package harborview.rapanui.core.domain.stockmarket.stock;

import harborview.shared.functional.Either;
import harborview.shared.util.stockmarket.StockMarketUtil;
import harborview.rapanui.kernel.error.Error;

public class Ticker {
    private final String ticker;
    private final int oid;

    private Ticker(String ticker, int oid) {
        this.ticker = ticker;
        this.oid = oid;
    }

    public static Either<Error,Ticker> create(String ticker) {
        var tickerU = ticker.toUpperCase();
        var oid = StockMarketUtil.mapString(tickerU);
        if (oid == null)  {
            return Either.left(businessErrorFor(tickerU));
        }
        else {
            return Either.right(new Ticker(tickerU, oid));
        }
    }

    public static Either<Error,Ticker> create(int oid) {
        var ticker = StockMarketUtil.mapOid(oid);
        if (ticker == null) {
            return Either.left(businessErrorFor(oid));
        }
        else {
            return Either.right(new Ticker(ticker, oid));
        }
    }

    private static Error businessErrorFor(String ticker) {
        var msg = String.format("No such ticker: %s", ticker);
        return new Error.BusinessError(msg);
    }
    private static Error businessErrorFor(int oid) {
        var msg = String.format("No such ticker oid: %d", oid);
        return new Error.BusinessError(msg);
    }

    public String getTicker() {
        return ticker;
    }

    public int getOid() {
        return oid;
    }
}
