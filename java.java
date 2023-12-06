import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIClient {

    public static void main(String[] args) {
        try {
            // Make an HTTP request
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.example.com/data"))
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Check if the request was successful (status code 200)
            if (response.statusCode() == 200) {
                // Parse JSON response into Java objects using Jackson ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                YourJavaObject yourJavaObject = objectMapper.readValue(response.body(), YourJavaObject.class);

                // Now you can work with your Java object
                System.out.println("Received data: " + yourJavaObject);
            } else {
                System.out.println("Error: Unable to fetch data. HTTP status code: " + response.statusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
