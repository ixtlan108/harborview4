package harborview.rapanui.service;

import harborview.rapanui.TongaRepository;
import harborview.rapanui.StockOptionResponse;
import harborview.shared.Core;
import harborview.shared.error.ApplicationError;
import harborview.shared.functional.Either;
import harborview.stockmarket.service.StockMarketService;
import harborview.rapanui.OptionPurchaseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RapanuiCore {
    private final Logger logger = LogManager.getLogger(RapanuiCore.class);

    private final StockMarketService stockMarketAdapter;
    private final TongaRepository tongaAdapter;
    private final Core core;

    public RapanuiCore(StockMarketService stockMarketAdapter,
                       TongaRepository tongaAdapter,
                       Core core) {
        this.stockMarketAdapter = stockMarketAdapter;
        this.tongaAdapter = tongaAdapter;
        this.core = core;
    }

    public Either<ApplicationError,List<OptionPurchaseDTO>> activePurchasesWithCritters(int purchaseType) {
        return core.handleSearch(() -> {
            var purchases = stockMarketAdapter.activePurchasesWithCritters(purchaseType);
            if (purchases == null) {
                logger.warn(String.format("Empty list for critters, purchaseType=%d", purchaseType));
                return Collections.emptyList();
            }
            return purchases.stream().map(OptionPurchaseDTO::new).
                    collect(Collectors.toList());
        });
    }

    public void toggleRule(int ruleId, boolean active, boolean isAccRule) {
        //==>>> stockMarketAdapter.toggleRule(ruleId, active, isAccRule);
    }
    public Either<ApplicationError, StockOptionResponse> stockOption(String ticker) {
        return core.handleSearch(() -> {
            var dto = tongaAdapter.stockOption(ticker);
            if (dto == null) {
                var msg = String.format("Empty stock option, ticker=%s", ticker);
                logger.warn(msg);
                return null;
            }
            return dto; //new StockOptionResponse(0.0, dto, 0, null);
        });
    }
}
