package harborview.rapanui.core.api.critter.usecase;

import harborview.rapanui.core.api.critter.query.FetchCrittersQuery;
import harborview.rapanui.kernel.dto.OptionPurchaseDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;
import org.springframework.stereotype.Component;

import java.util.List;

public interface FetchCrittersUseCase {
    Either<Error, List<OptionPurchaseDTO>> handle(FetchCrittersQuery command);
}
