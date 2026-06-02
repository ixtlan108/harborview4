package harborview.rapanui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
@RequestMapping("/rapanui")
public class RapanuiAPI {

    @RequestMapping(method =  RequestMethod.GET, path = "/home")
    public String rapanui(Locale locale, Model model) {
        return "rapanui/index";
    }

}
