import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DeleteAndPost extends BaseClass {
    
    @Test
    public void deleteIsSuccessful() throws IOException  {
        HttpDelete request = new HttpDelete("https://api.github.com/huseyinoran/swiftmessagingapp");
        response= client.execute(request);

        request.setHeader(HttpHeaders.AUTHORIZATION,"token " + Credentials.TOKEN);

        int actualStatusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatusCode,204);

    }

    @Test(description = "This test will work only if you set valid email+ password in the Credentials Class ")
    public void createRepoReturns201() throws IOException {

        // Create an HttpPost with a valid Endpoint
        HttpPost request = new HttpPost("https://api.github.com/user/repos");

        //Set  the Basic  Auth Header
        String auth = Credentials.EMAIL +":" + Credentials.PASSWORD;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic" + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION,authHeader);

        //Define Json to Post and set as Entity
        String json = "{\"name\": \"deleteme\"}";
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        //Send
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatusCode,201);
    }

    @Test(description = "This test will work only WebToken ")
    public void createRepoReturns201WebToken() throws IOException {

        // Create an HttpPost with a valid Endpoint
        HttpPost request = new HttpPost("https://api.github.com/user/repos");
        request.setHeader(HttpHeaders.AUTHORIZATION,"token " + Credentials.TOKEN);

        //Set  the Basic  Auth Header
        String json = "{\"name\": \"deleteme\"}";
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        //Send
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatusCode,201);
    }


}
