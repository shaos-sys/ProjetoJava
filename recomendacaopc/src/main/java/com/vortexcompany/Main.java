 package com.vortexcompany;

 import java.util.ArrayList;
 import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
     Scanner scan = new Scanner(System.in);  
     ArrayList<String> l = new ArrayList<>();
    
     ListaJogos lista01 = new ListaJogos();
     SpecPc listaSpec01 = new SpecPc();
     OllamaClient ollama = new OllamaClient();
     Menu m = new Menu();
     
       int opcao = 0;
       l = lista01.getLista();
       
        m.menuPrincipal();
        opcao = scan.nextInt();
        scan.nextLine();

       while (opcao != 8) { 
         switch (opcao) {

             case 1:
             lista01.setAdicionarJogos(scan);
              m.menuPrincipal();
              opcao = scan.nextInt();               
             break;

             case 2:
             lista01.removerJogos(scan);
              m.menuPrincipal();
              opcao = scan.nextInt();                               
             break;  

             case 3:
             if (l.isEmpty()) {
              System.out.println("A lista está vazia!");
              m.menuPrincipal();
              opcao = scan.nextInt(); 

             } else {
             lista01.exibirLista();
              m.menuPrincipal();
              opcao = scan.nextInt();
             }  
             break;
             
                case 4:{
                if (l.isEmpty()) {
                System.out.println("Lista vazia! Adicione jogos.");
                m.menuPrincipal();
                opcao = scan.nextInt();  
                break;   

                }
                System.out.println("Gerando recomendações...");
                 try{
                  String recomendacoes = ollama.gerarRecomendacao(l);
                  System.out.println("====== PC's ======");
                  System.out.println(recomendacoes);
              

                } catch(Exception e){
                 System.err.println("Erro ao conectar com Ollama.");
                 System.err.println("Certifique-se que a conexação com Ollama 'Ollama serve' está rodando.");
                }

                m.menuPrincipal();
                opcao = scan.nextInt();              
                break;
                }

             case 5:
             listaSpec01.setAdicionarSpec(scan);
             m.menuPrincipal();
             opcao = scan.nextInt();

             case 6:
             listaSpec01.exibirListaPC();
             m.menuPrincipal();
             opcao = scan.nextInt();  

             default:
             if (opcao >= 9) {
             System.out.println("Opção inválida!");
              m.menuPrincipal();
              opcao = scan.nextInt(); 
             }               
             break;               
          }         
       }  

    System.out.println("Encerrando...");   
   
    }
 }