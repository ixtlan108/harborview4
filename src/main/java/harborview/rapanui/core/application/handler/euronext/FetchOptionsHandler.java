package harborview.rapanui.core.application.handler.euronext;

import harborview.rapanui.core.spi.euronext.EuroNextPort;
import harborview.rapanui.kernel.dto.euronext.OptionDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;

import java.util.Collections;
import java.util.List;

public class FetchOptionsHandler {
    private final EuroNextPort euroNextPort;

    public FetchOptionsHandler(EuroNextPort euroNextPort) {
        this.euroNextPort = euroNextPort;
    }
}
