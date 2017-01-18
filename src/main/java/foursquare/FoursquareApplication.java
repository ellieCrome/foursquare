package foursquare;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by ecrome on 16/01/2017.
 */
public class FoursquareApplication extends Application<FoursquareApplicationConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(FoursquareApplication.class);

    public static void main(String[] args) throws Exception {
        new FoursquareApplication().run(args);
        logger.info("*** Foursquare Service started ***");
    }

    @Override
    public void run(FoursquareApplicationConfiguration configuration, Environment environment) throws Exception {
        FoursquareService foursquareService = new FoursquareService();
        environment.jersey().register(foursquareService);


        logger.info("** Environment initialised **");
    }

}
