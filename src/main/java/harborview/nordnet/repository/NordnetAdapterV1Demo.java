package harborview.nordnet.repository;

//import harborview.adapter.RedisAdapter;
import harborview.nordnet.downloader.Downloader;
import harborview.nordnet.downloader.PageInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("adapter.demo")
@Profile("demo")
public class NordnetAdapterV1Demo extends NordnetAdapterV1 {

    public NordnetAdapterV1Demo(Downloader<PageInfo> downloader) {
        super(downloader);
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
