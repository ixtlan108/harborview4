package harborview.rapanui.core.application.handler.euronext;

import harborview.rapanui.core.spi.euronext.EuroNextPort;
import harborview.rapanui.kernel.dto.euronext.OptionDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchOptionsHandler {
    private final EuroNextPort euroNextPort;

    public FetchOptionsHandler(EuroNextPort euroNextPort) {
        this.euroNextPort = euroNextPort;
    }
    public Either<Error,List<OptionDTO>> fetchOptions(String ticker) {
        return euroNextPort.fetchOptions(ticker);
    }
}
