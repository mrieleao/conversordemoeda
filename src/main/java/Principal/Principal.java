package Principal;

import Service.ConsumoApi;
import Service.ConversorDeMoedas;
import model.ApiResponse;

import java.util.Map;
import java.util.Scanner;

public class Principal {

    private static final Map<String, String> moedasDisponiveis = Map.of(
            "BRL", "Real Brasileiro",
            "USD", "Dólar Americano",
            "CAD", "Dólar Canadense",
            "EUR", "Euro",
            "GBP", "Libra Esterlina",
            "MXN", "Peso Mexicano",
            "ARS", "Peso Argentino"
    );

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ConversorDeMoedas conversorService = new ConversorDeMoedas();

        System.out.println("\n******************************************");
        System.out.println("Bem-vindo(a) ao Conversor de Moedas! Aqui você pode converter diferentes moedas.\n");
        exibirMoedasSuportadas();

        while (true) {
            try {
                String moedaOrigem = solicitarMoeda("\nQual a moeda que você deseja converter: ");
                String moedaDestino = solicitarMoeda("Digite a moeda para a qual você deseja converter: ");
                double valorAConverter = solicitarValor();

                ApiResponse moedaResponse = ConsumoApi.getMoedaResponse(moedaOrigem);
                conversorService.realizarConversao(moedaResponse, moedaDestino, valorAConverter);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            if (!continuarConversao()) {
                System.out.println("Obrigado por usar o Conversor de Moedas! Até a próxima.");
                break;
            }
        }
    }

    private static void exibirMoedasSuportadas() {
        System.out.println("Moedas disponíveis para conversão:");
        moedasDisponiveis.forEach((sigla, nome) -> System.out.println(sigla + " - " + nome));
    }

    private static String solicitarMoeda(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String moeda = scanner.nextLine().toUpperCase();
            if (moedasDisponiveis.containsKey(moeda)) {
                return moeda;
            } else {
                System.out.println("Moeda inválida. Por favor, insira uma moeda válida.");
            }
        }
    }

    private static double solicitarValor() {
        while (true) {
            System.out.print("Digite o valor que deseja converter: ");
            if (scanner.hasNextDouble()) {
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Limpa o buffer
                return valor;
            } else {
                System.out.println("Valor inválido. Por favor, insira um valor numérico.");
                scanner.nextLine(); // Limpa o buffer
            }
        }
    }

    private static boolean continuarConversao() {
        System.out.print("Gostaria de realizar outra conversão? (sim/não): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        return resposta.equals("sim");
    }
}
