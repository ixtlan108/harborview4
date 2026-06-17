package harborview.nordnet;

import harborview.stockmarket.service.StockMarketCore;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import java.time.LocalDate;

import static org.springframework.modulith.test.ApplicationModuleTest.BootstrapMode.ALL_DEPENDENCIES;


@ApplicationModuleTest(mode = ALL_DEPENDENCIES)
public class NordnetIntegrationTest {

    @Autowired
    private StockMarketCore core;

    @Autowired
    private SqlSession session;

    @Test
    void test() {

    }
}
