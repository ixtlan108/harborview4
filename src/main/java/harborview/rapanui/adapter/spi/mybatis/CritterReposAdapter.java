package harborview.rapanui.adapter.spi.mybatis;

import harborview.rapanui.core.domain.repository.CritterRepository;
import harborview.rapanui.core.domain.stockmarket.stockoption.StockOptionPurchase;
import harborview.rapanui.core.domain.value.stockmarket.PurchaseType;
import harborview.rapanui.kernel.dto.OptionPurchaseDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("prod")
@Component
public class CritterReposAdapter implements CritterRepository {


    protected final SqlSession session;

    public CritterReposAdapter(SqlSession session) {
        this.session = session;
    }

    @Override
    public Either<Error,List<StockOptionPurchase>> readAllCrittersByPurchaseType(PurchaseType purchaseType) {
        return Either.right(List.of());
    }

    @Override
    public Either<Error, List<OptionPurchaseDTO>> findAllCrittersByPurchaseType(PurchaseType purchaseType) {
        return null;
    }

}
