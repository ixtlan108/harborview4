package harborview.nordnet.repository;

//import harborview.adapter.RedisAdapter;
import harborview.nordnet.downloader.Downloader;
import harborview.nordnet.downloader.PageInfo;
import harborview.vega.OptionCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("adapter.dev")
@Profile("dev")
public class NordnetAdapterV1Dev extends NordnetAdapterV1 {

    public NordnetAdapterV1Dev(@Qualifier("dev") Downloader<PageInfo> downloader,
                               @Qualifier("blackScholes") OptionCalculator calculator,
                               @Value("${curdate:#{null}}") String curDateStr,
                               @Value("${cache.options.expiry.minutes}") int optionsExpiry,
                               @Value("${cache.option.expiry.seconds}") int optionExpiry,
                               @Value("${redis.fetchOpeningPrice}") boolean fetchOpeningPrice) {
        super(downloader,calculator,curDateStr,optionExpiry,optionExpiry,fetchOpeningPrice);
        System.out.println("HI, I AM THE ACTIVE ONE: " + this);
    }


    /*
    public NordnetAdapterV1Demo(@Qualifier("demo") Downloader<PageInfo> downloader,
                                RedisAdapter redisAdapter,
                                @Qualifier("blackScholes") OptionCalculator blackScholes,
                                @Value("${curdate:#{null}}") String curDateStr,
                                @Value("${cache.options.expiry}") int optionsExpiry,
                                @Value("${cache.option.expiry}") int optionExpiry,
                                @Value("${redis.fetchOpeningPrice}") boolean fetchOpeningPrice) {
        super(downloader,redisAdapter,blackScholes,curDateStr,optionsExpiry,optionExpiry,fetchOpeningPrice);
        System.out.println("HI, I AM THE ACTIVE ONE: " + this);
    }


     */
}
