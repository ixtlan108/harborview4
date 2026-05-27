package harborview.nordnet.vega.filters.ehlers;

import harborview.nordnet.vega.filters.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rcs on 14.09.17.
 *
 */
public class SuperSmoother implements Filter {

    private int cyclePeriod;

    public SuperSmoother(int cyclePeriod) {
        this.cyclePeriod = cyclePeriod;
    }

    @Override
    public List<Double> calculate(List<Double> data) {
        List<Double> result = new ArrayList<Double>();

        double twoPoleFactor = 1.414;

        double f = (twoPoleFactor * Math.PI) / cyclePeriod;

        double a = Math.exp(-f);

        double c2 = 2 * a * Math.cos(f);

        double c3 = - a * a;

        double c1 = 1 - c2 - c3;

        result.add(data.get(0));

        result.add(data.get(1));

        for (int i = 2; i<data.size(); ++i) {

            double close = data.get(i);
            double close_1 = data.get(i-1);
            double filt_1 = result.get(i-1);
            double filt_2 = result.get(i-2);
            double filt = (0.5*c1*(close + close_1)) + (c2*filt_1) + (c3*filt_2);

            result.add(filt);
        }

        return result;
    }

}

