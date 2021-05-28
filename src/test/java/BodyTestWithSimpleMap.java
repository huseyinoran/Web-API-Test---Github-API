import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static entities.User.ID;
import static entities.User.LOGIN;


public class BodyTestWithSimpleMap extends BaseClass {

    @Test
    public void returnsCorrectLogin() throws IOException, JSONException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        response = client.execute(get);

        String jsonBody = EntityUtils.toString(response.getEntity());

        JSONObject jsonObject = new JSONObject(jsonBody);

        String loginValue = (String) getValueFor(jsonObject, LOGIN);

        Assert.assertEquals(loginValue, "andrejss88");
    }

    @Test
    public void returnsCorrectId() throws IOException, JSONException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/huseyinoran");

        response = client.execute(get);

        String jsonBody = EntityUtils.toString(response.getEntity());

        JSONObject jsonObject = new JSONObject(jsonBody);

        Integer loginValue = (Integer) getValueFor(jsonObject, ID);

        Assert.assertEquals(loginValue, Integer.valueOf(32902556));
    }

    private Object getValueFor(JSONObject jsonObject, String key) throws JSONException {
        return jsonObject.get(key);
    }
}
