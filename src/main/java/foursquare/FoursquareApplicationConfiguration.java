package foursquare;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;


/**
 * Created by ecrome on 18/01/2017.
 */
public class FoursquareApplicationConfiguration extends Configuration {

    private JerseyClientConfiguration authJerseyClient;

    public JerseyClientConfiguration getAuthJerseyClient() {
        return authJerseyClient;
    }

    public void setAuthJerseyClient(JerseyClientConfiguration authJerseyClient) {
        this.authJerseyClient = authJerseyClient;
    }


}

