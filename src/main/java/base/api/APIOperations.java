package base.api;

import io.qameta.allure.Step;
import org.json.JSONObject;

import javax.ws.rs.core.Response;

/**
 * Class for prepared API requests for use in UI testing
 */
public class APIOperations {
    private final String ROOT_RC_URL = "";


    //USAGE EXAMPLE
    @Step("SOME ALLURE STEP NAME")
    public Response importedLeadsRefuse(String naming) {
        JSONObject body = new JSONObject();
        body.put("sourceTypeNum", naming);

        return new APIMethods(ROOT_RC_URL)
                .post(new PreparedHeaders().DataHeaders("lead/renounBySourceType"), body);
    }
}
