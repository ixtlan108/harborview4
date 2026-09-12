package harborview.rapanui.core.api.critter.usecase;

import harborview.rapanui.core.api.critter.command.FetchCrittersCommand;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;
import org.springframework.stereotype.Component;

@Component
public interface FetchCrittersUseCase {
    Either<Error.BusinessError, Void> handle(FetchCrittersCommand command);
}
