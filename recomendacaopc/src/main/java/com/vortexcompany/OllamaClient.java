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
 
 private final String URL_OLLAMA = "http://localhost:11434/api/generate";
 private final String MODEL = "gemma2:2b";
 private final Gson gson = new Gson();

     public String gerarRecomendacao(ArrayList<String> lista) throws Exception{
     String prompt = montarPrompt(lista); // Variável prompt criada, chamando o método montarPrompt com o parâmetro (lista).

     JsonObject body = new JsonObject(); // Aqui é instanciado a classe JsonObject -> Passa (como se fosse um envelope/caixa).
      body.addProperty("model", MODEL); // Chave model para json (envelope) para enviar para a IA pelo HttpRequest.
      body.addProperty("prompt", prompt); // Chave prompt, da variável prompt ja definida, novamente o "envelope" com o prompt.
      body.addProperty("stream", false); // Diz para a IA não transmitir a resposta aos pouco, em cadeia, e sim esperar ate a resposta estar completa.

     HttpClient client = HttpClient.newHttpClient();
     HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(URL_OLLAMA))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body))) 
        .build();
     HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Envia e guarda a resposta em uma String. 

     JsonObject resposta = gson.fromJson(response.body(), JsonObject.class); // Response é a variável que tem a resposta guardada, o JsonObject é a classe do GSON, o método gson.fromJson converte a resposta de json para objeto java.
      return resposta.get("response").getAsString();

     }

     public String montarPrompt(ArrayList<String> lista){
      StringBuilder sb = new StringBuilder();  
       sb.append("Você deve fazer recomendação de PC's gamer com base na lista\n\n");
       sb.append("Os jogos selecionados pelo usuário são:\n\n");

        // Aqui a IA passa e lê a lista. 
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



    
 }
