package harborview.rapanui.adapter.api;

import harborview.rapanui.core.api.critter.usecase.FetchCrittersUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rapanui")
public class RapanulAPI {

    private final FetchCrittersUseCase useCase;

    public RapanulAPI(FetchCrittersUseCase useCase) {
        this.useCase = useCase;
    }
}
