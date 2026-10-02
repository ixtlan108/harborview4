package harborview.rapanui.adapter.api;

import harborview.rapanui.core.api.critter.query.FetchCrittersQuery;
import harborview.rapanui.core.api.critter.usecase.FetchCrittersUseCase;
import harborview.rapanui.kernel.dto.OptionPurchaseDTO;
import harborview.rapanui.core.application.common.ApiUtil;
import harborview.shared.api.response.PayloadResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/rapanui")
public class RapanulAPI {

    private final FetchCrittersUseCase useCase;

    public RapanulAPI(FetchCrittersUseCase useCase) {
        this.useCase = useCase;
    }

    @RequestMapping(method =  RequestMethod.GET, path = "/home")
    public String rapanui(Locale locale, Model model) {
        return "rapanui/index";
    }

    @GetMapping(value = "/purchase/{ptype}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PayloadResponse<List<OptionPurchaseDTO>>> purchases(@PathVariable("ptype") int ptype) {
        var cmd = new FetchCrittersQuery(ptype);
        var result = useCase.handle(cmd);
        return ApiUtil.mapQuery(result);
    }
}
/*
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
public ApplicationError optionSales(OptionSalesRequest req) {
    return core.handleSave(() -> {

    },null);
}

 */


/*
    private final RapanuiCore core;

    public RapanuiAPI(RapanuiCore core) {
        this.core = core;
    }

    @RequestMapping(method =  RequestMethod.GET, path = "/home")
    public String rapanui(Locale locale, Model model) {
        return "rapanui/index";
    }

    @GetMapping(value = "/purchase/{ptype}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PayloadResponse<List<OptionPurchaseDTO>>> purchases(@PathVariable("ptype") int ptype) {
        return ApiUtil.map(core.activePurchasesWithCritters(ptype));
    }

    @GetMapping(value = "/stockoption/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PayloadResponse<StockOptionResponse>> stockOption(@PathVariable String ticker) {
        return ApiUtil.map(core.stockOption(ticker));
    }


    @PutMapping(value = "/optionsales", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> optionSales(@RequestBody OptionSalesRequest req) {
        var result = new DefaultResponse(0, "Sold");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }


 */
/*
com.example.product
│
├── core/                           <-- Pure Java (No Spring Web/Data dependencies)
│   ├── model/
│   │   └── Product.java            <-- Plain old Java object (POJO)
│   │
│   ├── ports/
│   │   ├── inbound/
│   │   │   └── CreateProductUseCase.java  <-- Interface (Driving Port)
│   │   └── outbound/
│   │       └── ProductRepositoryPort.java <-- Interface (Driven Port)
│   │
│   └── service/
│       └── ProductService.java     <-- Use-case implementation (No @Service annotation)
│
└── infrastructure/                 <-- Technology Layer (Spring lives here)
    └── adapters/
        ├── inbound/
        │   └── web/
        │       ├── ProductRestController.java <-- @RestController, HTTP parsing
        │       └── dto/
        │           └── ProductRequest.java    <-- JSON Web Request DTO
        │
        └── outbound/
            └── db/
                ├── PostgresProductRepository.java <-- Implements ProductRepositoryPort
                ├── SpringDataProductRepository.java <-- Extends JpaRepository
                └── entity/
                    └── ProductEntity.java     <-- @Entity with JPA annotations



In **hexagonal architecture**, **Data Transfer Objects (DTOs)** typically live in the **Application layer** or within the **Adapter/Infrastructure layer** (specifically in inbound and outbound gateways), but **never in the Domain layer**.

### Primary Locations

*   **Application Layer**: Many practitioners place DTOs here to manage data flow between the domain and external interfaces. This layer handles the mapping between DTOs and domain objects, keeping the domain free of infrastructure-specific concerns.
*   **Adapter/Infrastructure Layer**: DTOs are often defined within **inbound gateways** (e.g., REST controllers) and **outbound gateways** (e.g., database adapters). In this view, DTOs are part of the "send/receive external data" sections, serving as contracts between the application and external systems.
*   **Shared/Contract Module**: In distributed architectures, DTOs may reside in a **shared library** or artifact repository to define API contracts for multiple components.

### Key Principles

*   **Decoupling**: DTOs ensure that **domain objects** are not directly exposed to external adapters, protecting business logic from changes in external interfaces.
*   **Mapping**: The **Application layer** or **Adapters** are responsible for mapping DTOs to/from **domain objects** using mappers or assemblers.
*   **No Domain Dependencies**: The **Domain layer** should have no dependencies on DTOs, as DTOs are technical constructs for data transfer, not business concepts.

### Summary

| Layer | Role of DTOs |
| :--- | :--- |
| **Domain** | **None**. DTOs should not exist here to maintain purity and decoupling. |
| **Application** | **Common**. Manages DTOs, handles validation, and maps DTOs to domain objects. |
| **Infrastructure/Adapter** | **Common**. Defines DTOs for inbound (API) and outbound (Persistence) gateways. |
| **Shared** | **Optional**. Used for API contracts in distributed systems. |

**Conclusion**: While practices vary, the most common and architecturally sound approach is to place DTOs in the **Application layer** or within specific **Adapters**, ensuring the **Domain layer** remains independent of external data formats.


 */
