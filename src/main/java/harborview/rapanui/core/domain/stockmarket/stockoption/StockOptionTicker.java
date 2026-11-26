package harborview.rapanui.core.domain.stockmarket.stockoption;

import harborview.shared.dto.Month;
import harborview.shared.dto.OptionType;
import harborview.shared.functional.Either;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.util.stockmarket.StockMarketUtil;

import java.time.LocalDate;

public class StockOptionTicker {

    private final LocalDate thirdFriday;
    private final OptionType optionType;
    private final String stockMarketTicker;

    private StockOptionTicker(LocalDate thirdFriday,
                              OptionType optionType,
                              String stockMarketTicker) {
        this.thirdFriday = thirdFriday;
        this.optionType = optionType;
        this.stockMarketTicker = stockMarketTicker;
    }


    public static Either<Error, StockOptionTicker> create(OptionType ot, int year, Month month) {
        var tf = StockMarketUtil.thirdFriday(year, month.getIndex());
        return Either.right(new StockOptionTicker(tf, ot, "NA"));
    }

    public LocalDate getThirdFriday() {
        return thirdFriday;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public String getStockMarketTicker() {
        return stockMarketTicker;
    }
}
