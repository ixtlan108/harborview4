package harborview.rapanui.core.application.mapper;

import harborview.rapanui.core.domain.stockmarket.stockoption.StockOptionPurchase;
import harborview.rapanui.kernel.dto.OptionPurchaseDTO;

import java.util.List;
import java.util.stream.Collectors;

final public class CritterMapper {

    public static OptionPurchaseDTO toDto(StockOptionPurchase purchase) {
        return new OptionPurchaseDTO(purchase);
    }

    public static List<OptionPurchaseDTO> toDto(List<StockOptionPurchase> purchases) {
        var result = purchases.stream().map(CritterMapper::toDto).collect(Collectors.toList());
        return result;
    }

}
