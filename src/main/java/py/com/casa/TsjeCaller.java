package py.com.casa;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TsjeCaller {
    private static final Logger LOGGER = LoggerFactory.getLogger(TsjeCaller.class);
    public static Client CLIENTE;

    public DivulgacionItem obtenerDivulgacion(String url) {
        DivulgacionItem resp = null;
        try {

            Client client = getCliente();
            client.setConnectTimeout(5 * 60 * 1000);
            client.setReadTimeout(5 * 60 * 1000);

            WebResource webResource = client.resource(url);

            WebResource.Builder builder = webResource.getRequestBuilder();

            ClientResponse getResponse = builder.get(ClientResponse.class);

            int status = getResponse.getStatus();

            if (status != 200) {
                LOGGER.info("status: [{}]", status);
            }

            ObjectMapper mapper = new ObjectMapper();

            resp = mapper.readValue(getResponse.getEntity(String.class), DivulgacionItem.class);

        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return resp;
    }

    private Client getCliente() {
        if (CLIENTE == null) {
            CLIENTE = Client.create();
        }
        return CLIENTE;
    }
}
