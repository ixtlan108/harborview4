package harborview.rapanui.adapter.spi.euronext;

import harborview.rapanui.core.spi.euronext.EuroNextPort;
import harborview.rapanui.kernel.dto.euronext.OptionDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;

import java.util.Collections;
import java.util.List;

public class EuroNextAdapter implements EuroNextPort  {
    @Override
    public Either<Error, List<OptionDTO>> fetchOptions(String ticker) {
        return Either.right(Collections.emptyList());
    }
}
