package foursquare;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.omg.PortableServer.IdAssignmentPolicyValue.USER_ID;

/**
 * Created by ecrome on 16/01/2017.
 */
public class FoursquareClientTest {

    private static FoursquareClient foursquareClient;

    @BeforeClass
    public static void beforeClass() {
        foursquareClient = new FoursquareClient();
    }

    @Test
    public void whenValidNameIsSuppliedThenReturnSuccess() throws Exception {
        String name = "Brooklyn";
        String result = foursquareClient.getVenue(name);

        assertThat(result, is(equalTo("200")));
    }

}
