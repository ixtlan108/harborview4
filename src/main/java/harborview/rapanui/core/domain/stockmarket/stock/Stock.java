package harborview.rapanui.core.domain.stockmarket.stock;

public class Stock {

    private final Ticker ticker;
    private Price price;


    public Stock(Ticker ticker) {
        this.ticker = ticker;
    }

}
