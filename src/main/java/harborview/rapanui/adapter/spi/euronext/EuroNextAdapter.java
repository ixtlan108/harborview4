package harborview.rapanui.adapter.spi.euronext;

import harborview.rapanui.core.spi.euronext.EuroNextPort;
import harborview.rapanui.kernel.dto.euronext.OptionDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class EuroNextAdapter implements EuroNextPort  {
    private final int euroNextPort;

    public EuroNextAdapter(@Value("${euronext.port}") int euroNextPort) {
        this.euroNextPort = euroNextPort;
    }

    @Override
    public Either<Error, List<OptionDTO>> fetchOptions(String ticker) {
        return Either.right(Collections.emptyList());
    }
}
