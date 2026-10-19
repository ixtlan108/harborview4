package harborview.rapanui.kernel.dto.euronext;

import harborview.shared.dto.Month;
import harborview.shared.util.StockOptionUtil;
import tools.jackson.databind.JsonNode;

import javax.swing.text.html.Option;

public class OptionDTO {

    private OptionDTO() {

    }

    public static OptionDTO create(JsonNode node) {
        var strike = node.get("strike").asFloat();
        var c_bid = node.get("c_bid").asFloat();
        var c_ask = node.get("c_ask").asFloat();
        var p_bid = node.get("p_bid").asFloat();
        var p_ask = node.get("p_ask").asFloat();
        var maturityDate = node.get("maturityDate").asString();
        var splitDate = maturityDate.split(" ");
        var month = Month.fromString(splitDate[0]);
        var year = Integer.valueOf(splitDate[1]);
        var exerciseDate = StockOptionUtil.thirdFriday(year,month.getIndex());
        //"maturityDate": "Oct 2026",
        return null;
    }

}
