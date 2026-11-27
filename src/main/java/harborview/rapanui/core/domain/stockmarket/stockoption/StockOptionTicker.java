package harborview.rapanui.core.domain.stockmarket.stockoption;

import harborview.rapanui.core.domain.stockmarket.stock.Ticker;
import harborview.shared.dto.Month;
import harborview.shared.dto.OptionType;
import harborview.shared.functional.Either;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.util.stockmarket.StockMarketUtil;

import java.time.LocalDate;

public class StockOptionTicker {

    private final LocalDate thirdFriday;
    private final OptionType optionType;
    private String stockMarketTicker;
    private final Ticker stockTicker;

    private StockOptionTicker(Ticker stockTicker,
                              LocalDate thirdFriday,
                              OptionType optionType) {
        this.thirdFriday = thirdFriday;
        this.optionType = optionType;
        this.stockTicker = stockTicker;
    }


    public static Either<Error, StockOptionTicker> create(Ticker stockTicker, OptionType ot, int year, Month month) {
        var tf = StockMarketUtil.thirdFriday(year, month.getIndex());
        return Either.right(new StockOptionTicker(stockTicker, tf, ot));
    }

    public static Either<Error, StockOptionTicker> create(String stockMarketTicker) {

        return null;
    }

    public LocalDate getThirdFriday() {
        return thirdFriday;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public String getStockMarketTicker() {
        if (stockMarketTicker == null) {
            stockMarketTicker = "NA";
        }
        return stockMarketTicker;
    }
}
