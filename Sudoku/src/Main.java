import java.util.*;
import java.util.stream.Stream;

import Espacos.Espaco;
import Espacos.Quadro;
import Quadro.ModeloQuadro;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    private static Quadro quadro;

    private final static int TAMANHO = 9;

    public static void main(String[] args) {
        final var posicoes = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
        var opcao = -1;
        while (true) {
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> iniciarJogo(posicoes);
                case 2 -> inserirNumero();
                case 3 -> removerNumero();
                case 4 -> mostrarJogoAtual();
                case 5 -> verificarStatusJogo();
                case 6 -> limparJogo();
                case 7 -> finalizarJogo();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
            }
        }
    }

    private static void iniciarJogo(final Map<String, String> posicoes) {
        if (nonNull(quadro)) {
            System.out.println("O jogo já foi iniciado");
            return;
        }
    
        List<List<Espaco>> espacos = new ArrayList<>();
        for (int i = 0; i < TAMANHO; i++) {
            espacos.add(new ArrayList<>());
            for (int j = 0; j < TAMANHO; j++) {
                var posicaoConfig = posicoes.get("%s,%s".formatted(i, j));
                if (posicaoConfig == null) {
                    System.out.printf("Configuração da posição [%s,%s] não encontrada. Usando valores padrão.\n", i, j);
                    espacos.get(i).add(new Espaco(0, false)); // Valores padrão
                } else {
                    var esperado = Integer.parseInt(posicaoConfig.split(",")[0]);
                    var fixo = Boolean.parseBoolean(posicaoConfig.split(",")[1]);
                    var espacoAtual = new Espaco(esperado, fixo);
                    espacos.get(i).add(espacoAtual);
                }
            }
        }
    
        quadro = new Quadro(espacos);
        System.out.println("O jogo está pronto para começar");
    }

    private static void inserirNumero() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna em que o número será inserido");
        var col = obterNumeroValido(0, 8);
        System.out.println("Informe a linha em que o número será inserido");
        var row = obterNumeroValido(0, 8);
        System.out.printf("Informe o número que vai entrar na posição [%s,%s]\n", col, row);
        var valor = obterNumeroValido(1, 9);
        if (!quadro.trocarValor(col, row, valor)) {
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
        }
    }

    private static void removerNumero() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna em que o número será removido");
        var col = obterNumeroValido(0, 8);
        System.out.println("Informe a linha em que o número será removido");
        var row = obterNumeroValido(0, 8);
        if (!quadro.limparValor(col, row)) {
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
        }
    }

    private static void mostrarJogoAtual() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        var args = new Object[81];
        var argPos = 0;
        for (int i = 0; i < TAMANHO; i++) {
            for (var col : quadro.getEspacos()) {
                args[argPos++] = " " + ((isNull(col.get(i).getAtual())) ? " " : col.get(i).getAtual());
            }
        }
        System.out.println("Seu jogo se encontra da seguinte forma");
        System.out.printf((ModeloQuadro.modelo_Quadro) + "\n", args);
    }

    private static void verificarStatusJogo() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        if (quadro.jogoFinalizado()) {
            System.out.println("O jogo está finalizado com sucesso!");
        } else {
            System.out.println("O jogo ainda não está finalizado.");
        }
    }

    private static void limparJogo() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso?");
        var confirm = scanner.next();
        while (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não")) {
            System.out.println("Informe 'sim' ou 'não'");
            confirm = scanner.next();
        }

        if (confirm.equalsIgnoreCase("sim")) {
            quadro.resetar();
        }
    }

    private static void finalizarJogo() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        if (quadro.jogoFinalizado()) {
            System.out.println("Parabéns, você concluiu o jogo!");
            mostrarJogoAtual();
            quadro = null;
        } else {
            System.out.println("Você ainda precisa preencher algum espaço ou corrigir erros.");
        }
    }

    private static int obterNumeroValido(final int min, final int max) {
        var current = scanner.nextInt();
        while (current < min || current > max) {
            System.out.printf("Informe um número entre %s e %s\n", min, max);
            current = scanner.nextInt();
        }
        return current;
    }
}