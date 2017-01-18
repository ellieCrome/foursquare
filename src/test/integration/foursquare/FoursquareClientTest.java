package foursquare;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

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
        String result = foursquareClient.getPlace(name);

        assertTrue(result.contains("\"code\":200"));
    }

    @Test (expected = Exception.class)
    public void whenEmptyNameIsSuppliedThenReturnBadRequest() throws Exception {
        String name = "";
        String result = foursquareClient.getPlace(name);

        assertTrue(result.contains("\"code\":400"));
    }

    @Test (expected = Exception.class)
    public void whenNoNameIsSuppliedThenReturnBadRequest() throws Exception {
        String name = null;
        String result = foursquareClient.getPlace(name);

        assertTrue(result.contains("\"code\":400"));
    }

}
