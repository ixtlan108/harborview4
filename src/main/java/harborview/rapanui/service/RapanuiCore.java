package harborview.rapanui.service;

import harborview.rapanui.OptionSalesRequest;
import harborview.rapanui.TongaRepository;
import harborview.rapanui.StockOptionResponse;
import harborview.shared.Core;
import harborview.shared.error.ApplicationError;
import harborview.shared.functional.Either;
import harborview.stockmarket.service.StockMarketService;
import harborview.rapanui.kernel.dto.OptionPurchaseDTO;
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
    public ApplicationError optionSales(OptionSalesRequest req) {
        return core.handleSave(() -> {

        },null);
    }
}

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
