package Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.ApiResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    private static final String API_KEY = "b2e23cd04eb04d4abe6fd80e";

    public static ApiResponse getMoedaResponse(String baseCurrency) throws Exception {
        String urlString = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + baseCurrency;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na API. Código de status: " + response.statusCode());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), ApiResponse.class);
        }catch (Exception e) {
            System.out.println("Erro ao consumir a API: " + e.getMessage());
            throw e;
        }
    }
}

