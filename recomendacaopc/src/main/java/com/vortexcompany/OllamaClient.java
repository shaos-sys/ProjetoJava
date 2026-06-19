 package com.vortexcompany;

 import java.net.URI;
 import java.net.http.HttpClient;
 import java.net.http.HttpRequest;
 import java.net.http.HttpResponse;
 import java.util.ArrayList;
 import com.google.gson.Gson;
 import com.google.gson.JsonObject;

 public class OllamaClient {
 ListaJogos lista = new ListaJogos();
 SpecPc specLista = new SpecPc();
 
 private final String URL_OLLAMA = "http://localhost:11434/api/generate";
 private final String MODEL = "gemma2:2b";
 private final Gson gson = new Gson();

    public String gerarRecomendacao(ArrayList<String> lista) throws Exception{
    String prompt = montarPrompt(lista); 

     JsonObject body = new JsonObject(); 
      body.addProperty("model", MODEL); 
      body.addProperty("prompt", prompt); 
      body.addProperty("stream", false); 

     HttpClient client = HttpClient.newHttpClient();
     HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(URL_OLLAMA))
      .header("Content-Type", "application/json")
      .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body))) 
      .build();
     HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); 

     JsonObject resposta = gson.fromJson(response.body(), JsonObject.class); 
      return resposta.get("response").getAsString();
     }

          public String gerarTestePC(ArrayList<String> specList, ArrayList<String> lista) throws Exception{
           String promptSPEC = montarPromptSPECS(specList, lista);
 
          JsonObject bodySPEC = new JsonObject();
           bodySPEC.addProperty("model", MODEL);
           bodySPEC.addProperty("prompt", promptSPEC);
           bodySPEC.addProperty("stream", false);

          HttpClient clientSPEC = HttpClient.newHttpClient();
          HttpRequest requestSPEC = HttpRequest.newBuilder()
           .uri(URI.create(URL_OLLAMA))
           .header("Content-type", "application/json")
           .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(bodySPEC)))
           .build();
          HttpResponse<String> responseSPEC = clientSPEC.send(requestSPEC, HttpResponse.BodyHandlers.ofString());

          JsonObject respostaSPEC = gson.fromJson(responseSPEC.body(), JsonObject.class);

            return respostaSPEC.get("response").getAsString();
          }

             public String montarPrompt(ArrayList<String> lista){
             StringBuilder sb = new StringBuilder();  
             sb.append("Você deve fazer recomendação de PC's gamer com base na lista\n\n");
             sb.append("Os jogos selecionados pelo usuário são:\n\n");
 
              for (int i = 0; i < lista.size(); i++){
              sb.append(i + 1).append(". ").append(lista.get(i)).append("\n");
              }

             sb.append("\nA lista possui diversos jogos de PC, as recomendações devem ser feitas com base nesses jogos.\n");
             sb.append("Preciso de duas recomendações, uma de entrada e uma mais potente.\n");
             sb.append("As recomendações devem conter as peças principais do PC, como processador, memória RAM, GPU...\n");
             sb.append("Nas recomendações devem conter apenas as peças e a média de preço de cada recomendação.\n");
             sb.append("\nREGRAS IMPORTANTES \n");
             sb.append("As recomendações de especificações devem ser apenas com base nos jogos da lista.\n");
             sb.append("Sugerir apenas duas recomendações, uma de entrada e mais barata e outra mais cara e potente\n");
             sb.append("Devem ser recomendações de peças de computador, com base numa média dos requisitos mínimos e recomendados dos jogos da lista.");
             sb.append("Escrever tudo em português do Brasil\n");  
             return sb.toString();   
             }

                public String montarPromptSPECS(ArrayList<String> specLista, ArrayList<String> lista){
                StringBuilder st = new StringBuilder();

                st.append("Você deve analisar a lista de hardwares de PC e também analisar a lista dos jogos.\n");
                st.append("A lista dos hardwares é:\n ");
                 for (int j = 0; j < specLista.size(); j++){
                 st.append(j + 1).append(". ").append(specLista.get(j)).append("\n");
                 }   

                st.append("Ja a lista dos jogos é a seguinte:\n "); 
                  for (int k = 0; k < lista.size(); k++){
                  st.append(k + 1).append(". ").append(lista.get(k)).append("\n");
                  }

                st.append("Com base na lista dos hardwares e dos jogos, verifique se os hardwars da lista conseguem rodas os jogos da lista de jogos.\n");
                st.append("Deve-se fazer uma média de como os hardwares da lista de hardwares irão rodas os jogos da lista de jogos.\n");
                st.append("E assim, analisar se os hardwares irão rodar jogos de forma flúida, ou se podem apresentar certo tipo de travamento.\n");
                st.append("\n REGRAS IMPORTANTES \n");
                st.append("Deve-se levar me conta somente os hardwares da lista de hardwares e os jogos da lista de jogos.\n");
                st.append("Informar se o hardware vai conseguir rodar os jogos bem e flúidos, com as configurações gráficas adequadas para isso.\n");
                st.append("Ou informar que terá que reduzir os gráficos para rodar os jogos, ou até que não ira rodar bem, se for o caso, e sugerir upgrades\n");
                st.append("Escrever tudo em português do Brasil\n");
                return st.toString();
                }

    
 }
