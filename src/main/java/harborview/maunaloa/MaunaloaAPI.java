package harborview.maunaloa;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/maunaloa")
public class MaunaloaAPI {

    public MaunaloaAPI() {
    }

    @GetMapping(value = "/charts")
    public String charts() {
        return "maunaloa/charts";
    }

    @GetMapping(value = "/stockoption")
    public String stockOption() {
        return "derivatives/index";
    }

    @GetMapping(value = "/oldstockoption")
    public String oldstockOption() {
        return "maunaloa/options";
    }

    @GetMapping(value = "/stockoption/purchases")
    public String optionPurchases() {
        return "optionpurchase/optionpurchases";
    }

    /*
    @GetMapping(value = "/days/{oid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PayloadResponse<Charts>> days(@PathVariable int oid) {
        return ApiUtil.map(maunaloaCore.days(new StockTicker(oid)));
    }

     */

    /*
    @ResponseBody
    @GetMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE)
    public String demo() {
        var result = maunaloaCore.demo();
        return result;
        //return new Demo(result);
    }
    public static class Demo {
        private String msg;
        public Demo(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }
     */
}
