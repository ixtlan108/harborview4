package harborview.nordnet.repository;

import harborview.nordnet.downloader.Downloader;
import harborview.nordnet.downloader.PageInfo;
import harborview.vega.OptionCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NordnetAdapterV1FileSystem extends NordnetAdapterV1 {


    public NordnetAdapterV1FileSystem(Downloader<PageInfo> downloader,
                                      @Qualifier("blackScholes") OptionCalculator blackScholes,
                                      @Value("${curdate:#{null}}") String curDateStr,
                                      @Value("${cache.options.expiry.minutes}") int optionsExpiry,
                                      @Value("${cache.option.expiry.seconds}") int optionExpiry,
                                      @Value("${redis.fetchOpeningPrice}") boolean fetchOpeningPrice) {
        super(downloader,blackScholes,curDateStr,optionsExpiry,optionExpiry,fetchOpeningPrice);
    }

}
