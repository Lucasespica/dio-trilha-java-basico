import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) throws Exception {
        //Conhecer e importar a classe Scanner
        Scanner scanner = new Scanner(System.in);

        //Exibir as mensagens para o nosso usuário e capturar os valores
        System.out.println("Por favlor, digite o numero da Conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Por favor, digite o numero da Agência: ");
        String agencia = scanner.nextLine();

        System.out.println("Por favor, digite seu Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Por favor, digite seu Saldo: ");
        double saldo = scanner.nextDouble();

        //Exibir a mensagem conta criada
        System.out.println("Olá "+nome+", obrigado por criar uma conta em nosso banco, sua agência é: "+agencia+", conta: "+numero+" e seu saldo "+saldo+" já está disponível para saque.");        
    
        scanner.close();
    }
}
