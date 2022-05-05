package com.mycompany.damasp2;
        
import java.util.Scanner;

public class DamasP2 {
    public static void main(String[] args) {
        Tabuleiro tab = new Tabuleiro(8);
        tab.imprimirTabuleiro();
        Scanner sc = new Scanner(System.in);
        String cord;
        int r=0;
        while(true) {
            if(r%2==0){
                System.out.print("Jogador 1 escolha uma peca com uma cordenada e de a cordenada desejada:");
                cord = sc.nextLine();
                if(tab.moverPeca(cord)) {
                	// resolve error de movimento
                	System.out.println("Movimento Invalido!");
                	continue;
                }
               r++;
               tab.imprimirTabuleiro();
            }else{
                System.out.print("Jogador 2 escolha uma peca com uma cordenada e de a cordenada desejada:");
                cord = sc.nextLine();
                if(tab.moverPeca1(cord)) {
                	// resolve error de movimento
                	System.out.println("Movimento Invalido!");
                	continue;
                }
                r++;
                tab.imprimirTabuleiro();
            }
            if(tab.getContadorBrancas() == 0 || tab.getContadorPretas() == 0) {
            	System.out.println("Jogo acabou");
            	break;
            }
        }
        
    }
}
