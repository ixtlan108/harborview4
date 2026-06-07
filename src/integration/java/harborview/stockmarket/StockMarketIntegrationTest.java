package harborview.stockmarket;

import harborview.stockmarket.service.StockMarketCore;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import java.time.LocalDate;

import static org.springframework.modulith.test.ApplicationModuleTest.BootstrapMode.ALL_DEPENDENCIES;


@ApplicationModuleTest(mode = ALL_DEPENDENCIES)
public class StockMarketIntegrationTest {

    @Autowired
    private StockMarketCore core;

    @Autowired
    private SqlSession session;

    @Test
    void test_fetch_critters() {
        var purchases = core.getCritters(11);
        Assertions.assertEquals(1,purchases.size());

        var purchase = purchases.getFirst();

        Assertions.assertEquals("YAR7C800",purchase.getOptionName());
        Assertions.assertEquals("c", purchase.getOptionType());
        Assertions.assertEquals(2, purchase.getOptionId());
        //Assertions.assertEquals(2, purchase.getStockOptionBuy());

        Assertions.assertEquals("YAR",purchase.getTicker());
        Assertions.assertEquals(11,purchase.getPurchaseType());
        Assertions.assertEquals(LocalDate.of(2027,3,19),purchase.getExpiry());
        Assertions.assertEquals(LocalDate.of(2026,6,3),purchase.getLocalDx());
        Assertions.assertEquals(312.0, purchase.getPrice(), 0.01);
        Assertions.assertEquals(516.0, purchase.getSpotAtPurchase(), 0.01);
        Assertions.assertEquals(300.0, purchase.getBuyAtPurchase(), 0.01);
        Assertions.assertEquals(10, purchase.getVolume());

        var critters = purchase.getCritters();
        Assertions.assertEquals(1, critters.size());

        var critter = critters.getFirst();
        Assertions.assertEquals(7, critter.getStatus());
        Assertions.assertEquals(10, critter.getSellVolume());

        var accRules = critter.getAcceptRules();
        Assertions.assertEquals(1, accRules.size());

        var acc = accRules.getFirst();
        Assertions.assertEquals(16.0, acc.getAccValue(), 0.01);
        Assertions.assertEquals(7, acc.getRtyp());
        Assertions.assertEquals("y", acc.getActive());

    }
}
