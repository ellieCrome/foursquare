package foursquare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ecrome on 16/01/2017.
 */
public class FoursquareClient {

    private static final String CLIENT_ID = "IAEFXMK0W4YXFJTBUFSCZPCY3VAUIM1PLI3522CCV1A2IDAZ";
    private static final String CLIENT_SECRET = "EVL3LCTAPVQENNSFAVI1XMUNO4MKUF1QDWZPQMA55DTH5CP0";
    private static final String HOST = "https://api.foursquare.com/";
    private static final String ENDPOINT = "v2/venues/explore";

    private static final Logger logger = LoggerFactory.getLogger(FoursquareClient.class);


    public String getPlace(String nameOfPlace) throws Exception {
        Client client = ClientBuilder.newClient();

        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String url = HOST + ENDPOINT;

        WebTarget target = client.target(url)
                .queryParam("near", nameOfPlace)
                .queryParam("client_id",CLIENT_ID)
                .queryParam("client_secret", CLIENT_SECRET)
                .queryParam("v", currentDate);

        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        Response response = request.get();

        System.out.println(response);


        if (Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily())) {
            //TODO: parse to JSON

            String result = response.readEntity(String.class);
            return result;

        } else {
            logger.error("Status: " + response.getStatusInfo().getStatusCode() + " Reason: " + response.getStatusInfo().getReasonPhrase());
            throw new Exception(response.getStatusInfo().getReasonPhrase());
        }
    }
}
