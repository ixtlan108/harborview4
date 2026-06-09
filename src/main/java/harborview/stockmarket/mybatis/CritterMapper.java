package harborview.stockmarket.mybatis;

import harborview.stockmarket.critter.RuleType;
import harborview.stockmarket.stockoption.StockOptionPurchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CritterMapper {

    void toggleAcceptRule(@Param("oid") int oid, @Param("isActive") String isActive);

    @Select("select * from stockmarket.rule_types order by oid")
    @ResultMap("ruleTypeMap")
    List<RuleType> ruleTypes();

    List<StockOptionPurchase> activePurchasesWithCritters(@Param("purchaseType") int purchaseType);
    List<StockOptionPurchase> activePurchasesWithCrittersDev(@Param("purchaseType") int purchaseType);

}
