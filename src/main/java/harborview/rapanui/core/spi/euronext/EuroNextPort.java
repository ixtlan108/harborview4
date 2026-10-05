package harborview.rapanui.core.spi.euronext;

import harborview.rapanui.kernel.dto.euronext.OptionDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;

import java.util.List;

public interface EuroNextPort {
    Either<Error, List<OptionDTO>> fetchOptions(String ticker);
}
