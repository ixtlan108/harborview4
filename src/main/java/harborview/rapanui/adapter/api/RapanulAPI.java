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
