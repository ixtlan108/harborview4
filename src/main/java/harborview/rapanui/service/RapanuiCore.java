package harborview.rapanui.service;

import harborview.nordnet.NordnetFacade;
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
    private Logger logger = LogManager.getLogger(RapanuiCore.class);

    private final StockMarketService stockMarketAdapter;
    private final NordnetFacade nordnetFacade;
    private final Core core;

    public RapanuiCore(StockMarketService stockMarketAdapter, NordnetFacade nordnetFacade,
                       Core core) {
        this.stockMarketAdapter = stockMarketAdapter;
        this.nordnetFacade = nordnetFacade;
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
}
