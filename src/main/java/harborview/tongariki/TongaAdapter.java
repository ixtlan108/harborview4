package harborview.tongariki;

import harborview.rapanui.StockOptionDTO;
import harborview.rapanui.StockOptionResponse;
import harborview.rapanui.TongaRepository;
import org.springframework.stereotype.Component;

@Component
public class TongaAdapter implements TongaRepository {
    @Override
    public StockOptionResponse stockOption(String ticker) {
        var opt = new StockOptionDTO(10, 12);
        return new StockOptionResponse(0.0, opt, 0, null);
    }
}
