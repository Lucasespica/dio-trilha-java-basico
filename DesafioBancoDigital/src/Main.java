import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();
        cliente.setNome("Lucas");
        Conta contaCorrente = new ContaCorrente(cliente);
        Conta contaPoupanca = new ContaPoupanca(cliente);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Sacar");
            System.out.println("2. Depositar");
            System.out.println("3. Transferir");
            System.out.println("4. Ver Informações Comuns");
            System.out.println("5. Sair");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor para sacar:");
                    double valorSaque = scanner.nextDouble();
                    contaCorrente.sacar(valorSaque);
                    break;
                case 2:
                    System.out.println("Digite o valor para depositar:");
                    double valorDeposito = scanner.nextDouble();
                    contaCorrente.depositar(valorDeposito);
                    break;
                case 3:
                    System.out.println("Digite o valor para transferir:");
                    double valorTransferencia = scanner.nextDouble();
                    contaCorrente.transferir(valorTransferencia, contaPoupanca);
                    break;
                case 4:
                    System.out.println("Imprimindo informações comuns...");
                    contaCorrente.imprimirInfosComuns();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}