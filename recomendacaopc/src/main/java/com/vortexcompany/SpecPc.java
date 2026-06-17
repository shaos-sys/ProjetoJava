 package com.vortexcompany;

 import java.util.ArrayList;
 import java.util.Scanner;

 public class SpecPc {
 private ArrayList<String> listaSpec = new ArrayList<>();
 
 public String spec;

     public void setAdicionarSpec (Scanner scn){

      do {
       System.out.println("Informe suas configurações do PC [Processador, placa de vídeo e memoria RAM] ('N' para voltar)");
       spec = scn.nextLine();
        
        if(!spec.equalsIgnoreCase("N")){
        listaSpec.add(spec); 

        } 
      } while (!spec.equalsIgnoreCase("N"));  
     }

     public void exibirListaPC(){
        System.out.println("====== LISTA HARDWARE ======");
        System.out.println(listaSpec);
        System.out.println("============================");
     }

         public ArrayList<String> getListaSpec(){
         return listaSpec;
         }
    
 }
