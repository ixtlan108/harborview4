package harborview.rapanui;

import harborview.rapanui.service.RapanuiCore;
import harborview.shared.api.response.PayloadResponse;
import harborview.shared.api.util.ApiUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/rapanui")
public class RapanuiAPI {

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
    public ResponseEntity<PayloadResponse<StockOptionResponse>> optionSales(@PathVariable String ticker) {
        return null;
    }
}
