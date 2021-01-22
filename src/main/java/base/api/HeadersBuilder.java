package base.api;

import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for building a header object for the API
 */
public class HeadersBuilder extends JSONObject {
    /**
     * Adding an "accept" object to the header
     */
    public JSONObject putAccept(String value) throws JSONException {
        put("accept", value);
        return this;
    }

    /**
     * Adding a "request" object to the header
     */
    public JSONObject putRequest(String value) throws JSONException {
        put("request", value);
        return this;
    }

    /**
     * Adding an "endpoint" object to the header
     */
    public JSONObject putEndpoint(String value) throws JSONException {
        put("endpoint", value);
        return this;
    }

    /**
     * Adding a map with headers to the header
     */
    public JSONObject putHeaders(HashMap<String, Object> myHeaders) throws JSONException {
        put("headers", myHeaders);
        return this;
    }

    /**
     * Getting the "request" object
     */
    public String getRequest() throws JSONException {
        Object object = this.get("request");
        if (object instanceof String) {
            return (String) object;
        } else {
            throw new JSONException("JSONObject[" + quote("request") + "] not a string.");
        }
    }

    /**
     * Getting the "accept" object
     */
    public String getAccept() throws JSONException {
        Object object = this.get("accept");
        if (object instanceof String) {
            return (String) object;
        } else {
            throw new JSONException("JSONObject[" + quote("accept") + "] not a string.");
        }
    }

    /**
     * Getting the "endpoint" object
     */
    public String getEndpoint() throws JSONException {
        Object object = this.get("endpoint");
        if (object instanceof String) {
            return (String) object;
        } else {
            throw new JSONException("JSONObject[" + quote("endpoint") + "] not a string.");
        }
    }

    /**
     * Получение заголовков обьекта
     */
    public Map<String, Object> getHeaders() throws JSONException {
        JSONObject object = this.getJSONObject("headers");
        System.out.println(object.getClass());
        if (object.toMap() instanceof HashMap) {
            return object.toMap();
        } else {
            throw new JSONException("JSONObject[" + quote("headers") + "] not a Map.");
        }
    }

    /**
     * Получение заголовков обьекта в MultivaluedMap
     */
    public MultivaluedMap<String, Object> getHeadersMultiMap() throws JSONException {
        JSONObject object = this.getJSONObject("headers");
        MultivaluedMap<String, Object> multivaluedMap = new MultivaluedHashMap<>();
        object.toMap().forEach(multivaluedMap::add);
        return multivaluedMap;
    }
}