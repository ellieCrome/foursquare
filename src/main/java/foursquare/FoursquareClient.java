package foursquare;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ecrome on 16/01/2017.
 */
public class FoursquareClient {

    private static final String CLIENT_ID = "IAEFXMK0W4YXFJTBUFSCZPCY3VAUIM1PLI3522CCV1A2IDAZ";
    private static final String CLIENT_SECRET = "EVL3LCTAPVQENNSFAVI1XMUNO4MKUF1QDWZPQMA55DTH5CP0";
    private static final String HOST = "https://api.foursquare.com/";
    private static final String ENDPOINT = "v2/venues/search";
    private static String date = "20170114";


    public String getVenue(String name) throws Exception {
        String url = HOST + ENDPOINT + "?ll=40.7,-74&query=" + name + "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v=" + date;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        Response response = request.get();

        if (Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily())) {
            //TODO: add loggers....
            //TODO: parse to JSON

            String result = response.readEntity(String.class);
            return result;
        } else {
            throw new Exception(response.getStatusInfo().getReasonPhrase());
        }
    }
}
