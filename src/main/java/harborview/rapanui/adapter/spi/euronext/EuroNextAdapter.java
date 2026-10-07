package harborview.rapanui.adapter.spi.euronext;

import harborview.rapanui.core.spi.euronext.EuroNextPort;
import harborview.rapanui.kernel.dto.euronext.OptionDTO;
import harborview.rapanui.kernel.error.Error;
import harborview.shared.functional.Either;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class EuroNextAdapter implements EuroNextPort  {
    private final int euroNextPort;

    private HttpClient client;

    public EuroNextAdapter(@Value("${euronext.port}") int euroNextPort) {
        this.euroNextPort = euroNextPort;
    }

    @Override
    public Either<Error, List<OptionDTO>> fetchOptions(String ticker) {

        try {
            var result = new ArrayList<OptionDTO>();

            client = getClient();

            var tickerUrl = urlFor(ticker);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(tickerUrl))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(response.body());

                JsonNode simple = node.get("simple");
                JsonNode dataArray = simple.get(0).get("data");

                for (var data : dataArray) {
                    //System.out.println(data.get("strike").asFloat());
                    result.add(mapJsonNode(ticker,data));
                }
            } else {
                var msg = String.format("GET request failed. Status code: %s", response.statusCode());
                return Either.left(new Error.BusinessError(msg));
            }
            return Either.right(result);

        } catch (IOException | InterruptedException e) {
            return Either.left(new Error.BusinessError(e.getMessage()));
        }
    }

    private String urlFor(String ticker) {
        return String.format("http://localhost:3000/proxy/%s", ticker);
    }

    private HttpClient getClient() {
        if (client == null) {
            client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        }
        return client;
    }

    private OptionDTO mapJsonNode(String ticker, JsonNode node) {
        var strike = node.get("strike").asFloat();
        var c_bid = node.get("c_bid").asFloat();
        var c_ask = node.get("c_ask").asFloat();
        var p_bid = node.get("p_bid").asFloat();
        var p_ask = node.get("p_ask").asFloat();
        var maturityDate = node.get("maturityDate").asFloat();
        return new OptionDTO();
    }
}


/*
{
  "simple": [
    {
      "maturityDate": "Oct 2026",
      "volumeDate": "06/10/26",
      "data": [
        {
          "atTheMoney": false,
          "c_settl": "31.01",
          "c_last": "-",
          "c_bid": "28.50",
          "c_ask": "30.75",
          "c_link": "\u003Ca href=\"/nb/product/stock-options/YAR-DOSL/instrument?Class_symbol=YAR&amp;ps=pagesize&amp;pmd=maturitydates&amp;Class_exchange=DOSL&amp;fOrO=O&amp;cOrP=C&amp;sp=38500&amp;md=01-10-2026\" class=\"text-ui-picton-blue font-weight-bold\"\u003EC\u003C/a\u003E",
          "strike": "385.00",
          "p_link": "\u003Ca href=\"/nb/product/stock-options/YAR-DOSL/instrument?Class_symbol=YAR&amp;ps=pagesize&amp;pmd=maturitydates&amp;Class_exchange=DOSL&amp;fOrO=O&amp;cOrP=P&amp;sp=38500&amp;md=01-10-2026\" class=\"text-ui-picton-blue font-weight-bold\"\u003EP\u003C/a\u003E",
          "p_bid": "1.45",
          "p_ask": "2.00",
          "p_last": "-",
          "p_settl": "1.79"
        },
       ]
  }
 */