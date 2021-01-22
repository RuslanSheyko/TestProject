package base.api;

import javax.ws.rs.core.MediaType;

/**
 * Prepared Headers
 */
public class PreparedHeaders {
    /**
     * Standard header object
     *
     * @param endpoint - endpoint
     * @return - header object
     */
    public HeadersBuilder DataHeaders(String endpoint) {
        HeadersBuilder apiHeaders = new HeadersBuilder();
        apiHeaders.putAccept(MediaType.APPLICATION_JSON);
        apiHeaders.putRequest(MediaType.APPLICATION_JSON);
        apiHeaders.putEndpoint(endpoint);
        return apiHeaders;
    }
}
