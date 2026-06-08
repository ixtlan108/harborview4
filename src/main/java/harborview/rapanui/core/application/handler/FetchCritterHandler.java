package harborview.rapanui.core.application.handler;

import harborview.rapanui.core.api.critter.query.FetchCrittersQuery;
import harborview.rapanui.core.api.critter.usecase.FetchCrittersUseCase;
import harborview.rapanui.core.application.mapper.CritterMapper;
import harborview.rapanui.core.domain.repository.CritterRepository;
import harborview.rapanui.core.domain.value.stockmarket.PurchaseType;
import harborview.rapanui.kernel.dto.OptionPurchaseDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchCritterHandler implements FetchCrittersUseCase {
    private final CritterRepository repository;

    public FetchCritterHandler(CritterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<Error, List<OptionPurchaseDTO>> handle(FetchCrittersQuery command) {
        var purchaseType = new PurchaseType(command.value());
        var result = repository.readAllCrittersByPurchaseType(purchaseType);
        if (result.isLeft()) {
            return Either.left(result.getLeft());
        }
        else {
            var dto = CritterMapper.toDto(result.getRight());
            return Either.right(dto);
        }
    }
}
