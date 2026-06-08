package harborview.rapanui.adapter.spi.mybatis;

import harborview.rapanui.adapter.spi.mybatis.mapper.CritterMapper;
import harborview.rapanui.core.application.common.Handler;
import harborview.rapanui.core.domain.stockmarket.stockoption.StockOptionPurchase;
import harborview.rapanui.core.domain.value.stockmarket.PurchaseType;
import harborview.rapanui.kernel.dto.OptionPurchaseDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("dev")
@Component
public class CritterReposAdapterDev extends CritterReposAdapter {

    public CritterReposAdapterDev(SqlSession session) {
        super(session);
    }

    @Override
    public Either<Error,List<StockOptionPurchase>> readAllCrittersByPurchaseType(PurchaseType purchaseType) {
        var mapper = session.getMapper(CritterMapper.class);
        return Handler.handleQuery(() -> mapper.activePurchasesWithCrittersDev(purchaseType.value()));
    }

    @Override
    public Either<Error, List<OptionPurchaseDTO>> findAllCrittersByPurchaseType(PurchaseType purchaseType) {
        var mapper = session.getMapper(CritterMapper.class);
        var result = Handler.handleQuery(() -> mapper.activePurchasesWithCrittersDev(purchaseType.value()));

        return null;
    }
}
