package harborview.rapanui.core.domain.repository;

import harborview.rapanui.core.domain.stockmarket.stockoption.StockOptionPurchase;
import harborview.rapanui.core.domain.value.stockmarket.PurchaseType;
import harborview.rapanui.kernel.dto.OptionPurchaseDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;

import java.util.List;

public interface CritterRepository {
    Either<Error,List<StockOptionPurchase>> readAllCrittersByPurchaseType(PurchaseType purchaseType);
    Either<Error,List<OptionPurchaseDTO>> findAllCrittersByPurchaseType(PurchaseType purchaseType);
}
