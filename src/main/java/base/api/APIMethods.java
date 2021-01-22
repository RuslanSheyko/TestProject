package base.api;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

/**
 * A builder class for building an API request
 */
@Slf4j
public class APIMethods {

    private final String rootURL;

    public APIMethods(String rootURL) {
        this.rootURL = rootURL;
    }

    /**
     * API request logging
     *
     * @param headers  - header
     * @param body     - body
     * @param response - request response
     */
    public static void logs(HeadersBuilder headers, JSONObject body, Response response) {
        log.info("------------------------------------------------------------------------------------------");
        log.info("Date: " + response.getDate());
        log.info("URI: " + createSubString(response.toString(), " uri=", ", status"));
        log.info("HTTP Method: " + createSubString(response.toString(), "method=", ","));

        log.info("Request headers: " + headers);
        if (body != null) {
            log.info("Request body: " + body.toString());
        } else {
            log.info("Request body: ");
        }
        log.info("------------------------------------------------------------------------------------------");
        log.info("Response HTTP Code: " + response.getStatus());
        if (response.getHeaders() != null) {
            log.info("Response headers: " + response.getHeaders().toString());
        } else {
            log.info("Response headers: ");
        }
        log.info("Response body: " + response.readEntity(String.class));
        log.info("------------------------------------------------------------------------------------------");
    }

    /**
     * Method for getting sub-strings with string search
     *
     * @param fullString - full string
     * @param start      - starting substring
     * @param end        - end substring
     * @return - the resulting substring
     */
    public static String createSubString(String fullString, String start, String end) {
        return fullString.substring(fullString.indexOf(start) + start.length(),
                fullString.indexOf(end));
    }

    /**
     * Header builder
     *
     * @param headers - header object
     * @return returns the request object
     */
    public Invocation.Builder buildRequest(HeadersBuilder headers) {
        Client client = ClientBuilder.newClient();
        if (headers.has("headers")) {
            return client
                    .target(rootURL).path(headers.getEndpoint())
                    .request(headers.getRequest())
                    .headers(headers.getHeadersMultiMap())
                    .accept(headers.getAccept());
        } else
            return client
                    .target(rootURL).path(headers.getEndpoint())
                    .request(headers.getRequest())
                    .accept(headers.getAccept());
    }

    /**
     * Method for POST request
     *
     * @param headers - header object
     * @param body    - request body
     * @return - response object
     */
    public Response post(HeadersBuilder headers, JSONObject body) {
        Response res = buildRequest(headers).post(Entity.json(body.toString()));
        res.bufferEntity();
        logs(headers, body, res);
        return res;
    }
}
