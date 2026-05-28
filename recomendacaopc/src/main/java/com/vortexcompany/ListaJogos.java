 package com.vortexcompany;

 import java.util.ArrayList;
 import java.util.Scanner;

public class ListaJogos {

 private ArrayList<String> listaJogos = new ArrayList<>();

 public String jg;

     public void setAdicionarJogos(Scanner sc){
     do {
       System.out.println("Digite um jogo (ou 'N' para sair).");
       jg = sc.nextLine();
                   
       if (!jg.equalsIgnoreCase("N")) {
        listaJogos.add(jg);
       }
      } while (!jg.equalsIgnoreCase("N"));
     }

     public void exibirLista(){
     System.out.println("====== LISTA ======");
     System.out.println(listaJogos);
     System.out.println("===================");
     }

         public void removerJogos (Scanner sc){
         System.out.println("====== REMOVER JOGO ======");  
         System.out.println("Informe a posição do jogo na lista a ser removido (começando do 0): ");
         System.out.println(listaJogos);
         int i = sc.nextInt();

           try {
           listaJogos.remove(i);   
           } catch(IndexOutOfBoundsException e) {
           System.out.println("ERRO! Posição inválida.");
           e.getMessage();       
           }     
          }
          
     public ArrayList<String> getLista(){
     return listaJogos;
     }     
}
