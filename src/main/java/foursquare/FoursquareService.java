package foursquare;

/**
 * Created by ecrome on 17/01/2017.
 */
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/places")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FoursquareService {

    private FoursquareClient foursquareClient;


    @GET
    @Path("/recommended/{location}")
    @Timed
    @ExceptionMetered
    public String getRecommendedPlaces(@PathParam("location") String location) throws Exception {
            return foursquareClient.getPlace(location);
    }
}
