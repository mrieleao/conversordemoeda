package Service;
import model.ApiResponse;
import java.util.Map;

public class ConversorDeMoedas {
    public void realizarConversao(ApiResponse moedaResponse, String moedaDestino, double valorParaConverter) {
        Map<String, Double> taxasConversao = moedaResponse.getConversionRates();

        if (!taxasConversao.containsKey(moedaDestino)) {
            throw new IllegalArgumentException("Moeda para conversão não encontrada.");
        }

        double taxaConversao = taxasConversao.get(moedaDestino);
        double valorConvertido = valorParaConverter * taxaConversao;

        // Exibindo as moedas de origem e destino corretamente
        System.out.printf("Taxa de conversão (%s -> %s): %.2f%n", moedaResponse.getResult(), moedaDestino, taxaConversao);
        System.out.printf("Valor convertido: %.2f%n", valorConvertido);
    }

}

