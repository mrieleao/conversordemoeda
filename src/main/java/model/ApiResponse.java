package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    @JsonProperty("result")
    private String result;

    @JsonProperty("conversion_rates")
    private Map<String, Double> conversionRates;

    public ApiResponse() {
        this.result = "";
        this.conversionRates = Map.of(); // Inicializa com um mapa imutável vazio
    }

    public String getResult() {
        return result;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
}

