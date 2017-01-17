package foursquare;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by ecrome on 16/01/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientBuilder.class)
public class FoursquareClientUnitTest {
    @Mock
    private Client client;
    @Mock
    private Invocation.Builder invocationBuilder;
    @Mock
    private WebTarget target;
    @Mock
    private Response response;
    @Mock
    private Response.StatusType statusType;

    @InjectMocks
    private FoursquareClient foursquareClient;

    @Before
    public void before() throws Exception {
        initMocks(this);
        PowerMockito.mockStatic(ClientBuilder.class);
        PowerMockito.when(ClientBuilder.newClient()).thenReturn(this.client);
        when(ClientBuilder.newClient()).thenReturn(client);
        when(client.target(anyString())).thenReturn(target);
        when(target.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.get()).thenReturn(response);
        when(response.getStatusInfo()).thenReturn(statusType);
        when(statusType.getFamily()).thenReturn(Response.Status.Family.SUCCESSFUL);
    }


    @Test
    public void whenNameisValidThenRetriveVenues() throws Exception {
        String jsonResponse = "{\"meta\":{\"code\":\"200\"}}";
        when(response.readEntity(String.class)).thenReturn(jsonResponse);

        String venues = foursquareClient.getVenue("London");

        assertThat(venues, is(equalTo(jsonResponse)));
    }

    @Test(expected = Exception.class)
    public void whenServerErrorOccursThenThrowException() throws Exception {
        when(statusType.getFamily()).thenReturn(Response.Status.Family.SERVER_ERROR);

        foursquareClient.getVenue("London");
    }

    @Test(expected = Exception.class)
    public void whenClientErrorOccursThenThrowException() throws Exception {
        when(statusType.getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);

        foursquareClient.getVenue("London");
    }

    //TODO: fail to parse exceptions
}
