package protocol.http;

import com.alibaba.fastjson.JSONObject;
import framework.Invocation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class HttpClient {
    public String send(String hostname, Integer port, Invocation invocation) {
        try {
//            URL url = new URL("http", hostname, port, "/");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//
//            OutputStream os = connection.getOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(os);
//            oos.writeObject(invocation);
//            oos.flush();
//            oos.close();
//
//            InputStream inputStream = connection.getInputStream();
//            String result = IOUtils.toString(inputStream, "utf-8");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http",null, hostname, port, "/", null, null))
                    .POST(HttpRequest.BodyPublishers.ofString(JSONObject.toJSONString(invocation)))
                    .build();

            HttpResponse<String> response = java.net.http.HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String result = response.body();
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
