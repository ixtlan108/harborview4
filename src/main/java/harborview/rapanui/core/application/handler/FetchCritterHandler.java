package harborview.rapanui.core.application.handler;

import harborview.rapanui.core.api.critter.command.FetchCrittersCommand;
import harborview.rapanui.core.api.critter.usecase.FetchCrittersUseCase;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;

public class FetchCritterHandler implements FetchCrittersUseCase {
    @Override
    public Either<Error.BusinessError, Void> handle(FetchCrittersCommand command) {
        return Either.right(null);
    }
}
