package harborview.rapanui.kernel.dto.euronext;

import harborview.shared.dto.OptionType;
import tools.jackson.databind.JsonNode;


public class OptionPriceDTO {

    public record BidAsk(double bid, double ask) {}

    private final double spot;
    private final BidAsk option;
    private final int optionStatus;
    private final String msg;

    public double getSpot() {
        return spot;
    }

    public BidAsk getOption() {
        return option;
    }

    public int getOptionStatus() {
        return optionStatus;
    }

    public String getMsg() {
        return msg;
    }



    private OptionPriceDTO(double spot, BidAsk option, int optionStatus, String msg) {
        this.spot = spot;
        this.option = option;
        this.optionStatus = optionStatus;
        this.msg = msg;
    }

    public static OptionPriceDTO create(JsonNode node, OptionType ot) {
        var bidS = ot == OptionType.CALL ? "c_bid" : "p_bid";
        var askS = ot == OptionType.CALL ? "c_ask" : "p_ask";

        var bid = node.get(bidS).asFloat();
        var ask = node.get(askS).asFloat();

        var bidAsk = new BidAsk(bid,ask);
        return new OptionPriceDTO(0.0, bidAsk, 0, null);

        /*
        var strike = node.get("strike").asFloat();
        var c_bid = node.get("c_bid").asFloat();
        var c_ask = node.get("c_ask").asFloat();
        var p_bid = node.get("p_bid").asFloat();
        var p_ask = node.get("p_ask").asFloat();
        var maturityDate = node.get("maturityDate").asString();
        var splitDate = maturityDate.split(" ");
        var month = Month.fromString(splitDate[0]);
        var year = Integer.parseInt(splitDate[1]);
        var exerciseDate = StockMarketUtil.thirdFriday(year,month.getIndex());
        //"maturityDate": "Oct 2026",
        return null;

         */
    }

}
