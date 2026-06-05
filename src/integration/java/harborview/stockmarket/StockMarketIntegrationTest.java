package harborview.stockmarket;

import harborview.stockmarket.service.StockMarketCore;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.springframework.modulith.test.ApplicationModuleTest.BootstrapMode.ALL_DEPENDENCIES;


@ApplicationModuleTest(mode = ALL_DEPENDENCIES)
public class StockMarketIntegrationTest {

    @Autowired
    private StockMarketCore core;

    @Autowired
    private SqlSession session;

    @Test
    void test_fetch_critters() {
        var critters = core.getCritters(11);
        Assertions.assertEquals(1,critters.size());
    }
}
