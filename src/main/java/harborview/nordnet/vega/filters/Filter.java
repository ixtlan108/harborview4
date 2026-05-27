package harborview.nordnet.vega.filters;

import java.util.List;

public interface Filter {
    List<Double> calculate(List<Double> data);
}
