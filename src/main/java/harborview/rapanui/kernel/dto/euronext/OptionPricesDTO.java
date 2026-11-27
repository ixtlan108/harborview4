package harborview.rapanui.kernel.dto.euronext;

import java.util.List;

public record OptionPricesDTO(String maturityDate, List<OptionPriceDTO> prices) {
}
