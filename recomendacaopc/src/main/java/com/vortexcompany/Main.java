 package com.vortexcompany;

 import java.util.ArrayList;
 import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
     Scanner scan = new Scanner(System.in);  

     ArrayList<String> l = new ArrayList<>();
     ArrayList<String> pc = new ArrayList<>();
    
     ListaJogos lista01 = new ListaJogos();
     SpecPc listaSpec01 = new SpecPc();
     OllamaClient ollama = new OllamaClient();
     Menu m = new Menu();
     
       int opcao = 0;

        l = lista01.getLista();
        pc = listaSpec01.getListaSpec();
       
        m.menuPrincipal();
        opcao = scan.nextInt();
        scan.nextLine();

       while (opcao != 8) { 
          switch (opcao) {

             case 1:
             lista01.setAdicionarJogos(scan);
              m.menuPrincipal();
              opcao = scan.nextInt();   
              scan.nextLine();            
             break;

             case 2:
             lista01.removerJogos(scan);
              m.menuPrincipal();
              opcao = scan.nextInt();    
              scan.nextLine();                           
             break;  

             case 3:
             if (l.isEmpty()) {
              System.out.println("A lista está vazia!");
              m.menuPrincipal();
              opcao = scan.nextInt(); 
              scan.nextLine();

             } else {
             lista01.exibirLista();
              m.menuPrincipal();
              opcao = scan.nextInt();
              scan.nextLine();
             }  
             break;
             
                case 4:{
                 if (l.isEmpty()) {
                System.out.println("Lista vazia! Adicione jogos.");
                 m.menuPrincipal();
                  opcao = scan.nextInt();  
                  scan.nextLine();
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
                scan.nextLine();          
                break;
                }

             case 5:
             listaSpec01.setAdicionarSpec(scan);
             m.menuPrincipal();
             opcao = scan.nextInt();
             scan.nextLine();
             break;

             case 6:
             if (pc.isEmpty()) {
              System.out.println("A lista está vazia!");
              m.menuPrincipal();
              opcao = scan.nextInt(); 
              scan.nextLine();

             } else {
             listaSpec01.exibirListaPC();
              m.menuPrincipal();
              opcao = scan.nextInt();
              scan.nextLine();
             }  
             break;

                case 7:{
                 if (pc.isEmpty()){
                System.out.println("Lista de hardware está vazia!");
                 m.menuPrincipal();
                  opcao = scan.nextInt();
                  scan.nextLine();
                break;
                }  

                System.out.println("Gerando avaliação do PC...");
                 try {
                  String avaliacaoPC = ollama.gerarTestePC(pc, l);
                  System.out.println("====== AVALIAÇÃO ======");
                  System.out.println(avaliacaoPC);
                  
                } catch (Exception e) {
                 System.err.println("Erro ao conectar com Ollama.");
                 System.err.println("Certifique-se que a conexação com Ollama 'Ollama serve' está rodando.");                                
                }

                m.menuPrincipal();
                opcao = scan.nextInt();
                scan.nextLine();
                break;
                }
               
             default:
             System.out.println("Opção inválida!");
              m.menuPrincipal();
               opcao = scan.nextInt();  
               scan.nextLine();          
             break;               
          }         
       }  

    System.out.println("Encerrando...");   
   
    }
 }