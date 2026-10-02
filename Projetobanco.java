package Minibanco;

// Projeto de Estudo
//     Conceitos praticados:
//         Livro: Think Java 2ed./**
//             -variaveis e tipos primitivos
//             -entrada de dados com scanner
//             -metodos de void e retorno
//             -condicionais (if, else, elseif)
//             -booleanos e variações
//             -iterações com for, do while e while
//             -escopo de variaveis
// @autor: Peterson Rodrigues Alvarado
// @version: 1.0
         
import java.util.Scanner;

        public class MiniBanco {
            //constantes
            static final double LIMITE_SAQUE = 1000.00; //valor limite
            static final double TAXA_SAQUE = 0.02; //taxa de saque

            static void exibirExtrato(String[] extrato, int totalLinhas){
                System.out.println("\n====EXTRATO====");
                if(totalLinhas == 0){
                    System.out.println("\nNenhuma movimentação registrada.");
                }else {
                    for (int i =0; i < totalLinhas;i++){
                        System.out.println(" "+ extrato[i]);
                    }
                }
                System.out.println("========================================");
            }

            static int registrar(String[] extrato, int totalLinhas, String linha){
                extrato[totalLinhas] = linha;
                return  totalLinhas + 1;
            }


            static double sacar(double saldo, double valor){
                return saldo - calcularTotalSaque(valor);
            }
            static double calcularTotalSaque(double valor){
                return valor + (valor * TAXA_SAQUE);
            }

            static boolean dentroDolimite(double valor){
                return valor <=LIMITE_SAQUE;
            }

            static boolean saldoSuficiente(double saldo, double valor){
                return saldo >= calcularTotalSaque(valor);
            }

            static boolean valorEhValido(double valor){
                return valor > 0;
            }

            static double depositar(double saldo, double valor){
                return saldo + valor;
            }

            static void exibirSaldo(double saldo){
                System.out.printf(" Saldo atual: R$ %.2f%n", saldo);
            }

            static void exibirMenu(){
                System.out.println("\n=== MINI BANCO ===");
                System.out.println("1 - Depositar");
                System.out.println("2 - Sacar");
                System.out.println("3 - Consultar saldo");
                System.out.println("4 - Ver extrato");
                System.out.println("0 - Sair");
                System.out.println("Digite uma das opções: ");
            }
            public static void main(String[] args) {
                //entrada de dados
        Scanner scanner = new Scanner(System.in);

        double saldo = 0.0;//saldo inicial sempre 0

        int opcao =1;//opção de menu
        

        String extrato[] = new String[50];
        int totalLinhas = 0;


        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        //System.out.printf("Olá, %s! Saldo inicial: R$:%.2f%n", nome, saldo);

        while (opcao !=0){
            exibirMenu();
            opcao = scanner.nextInt();

            if(opcao ==1){
                //fluxo do depósito
                //System.out.println("Depositar - Em breve");
                System.out.println("Valor a depositar: R$ ");
                double valor = scanner.nextDouble();

                if(!valorEhValido(valor)){
                    System.out.println("Valor deve ser maior que zero!");
                 }else {
                    saldo = depositar(saldo, valor);
                    System.out.println("valor depositado com sucesso!");
                     exibirSaldo(saldo);
                     totalLinhas = registrar(extrato, totalLinhas, String.format("DEPÓSITO + R$ %.2f -> Saldo: R$ %.2f", valor, saldo));
                }

            }else if (opcao == 2){
                //fluxo saque
                //System.out.println("Sacar - Em breve");
                System.out.println("valor a sacar: R$ ");
                double valorSaque = scanner.nextDouble();

                    if(!valorEhValido(valorSaque)){
                    System.out.printf("Valor inválido!");
                    }else if(!dentroDolimite(valorSaque)){
                    System.out.printf("Limite excedido. Máximo: R$ %.2f%n", LIMITE_SAQUE);
                    }else if(!saldoSuficiente(saldo, valorSaque)){
                    System.out.printf("Saldo insuficiente. Necessário: R$ %.2f%n", calcularTotalSaque(valorSaque));
                    }else {
                    double taxa = valorSaque * TAXA_SAQUE;
                    saldo = sacar(saldo, valorSaque);
                    System.out.printf("Saque realizado. Taxa cobrada: R$ %.2f%n", taxa);
                    exibirSaldo(saldo);
                    totalLinhas = registrar(extrato, totalLinhas, String.format("SAQUE -R$ %.2f -> Saldo: R$%.2f", valorSaque, saldo));
                }
            }else if (opcao == 3){
                //consulta de saldo
                exibirSaldo(saldo);
            }else if (opcao == 4){
                //System.out.println("Extrato = Em breve");
                exibirExtrato(extrato, totalLinhas);
            }else if (opcao == 0){
                exibirExtrato(extrato, totalLinhas);
                System.out.println("até logo " + nome + "!");
            }else {
                System.out.println("opção invalida. Tente novamente");
            }

        }
    }
}
