package com.reside.residebackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.StackTrace;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagesApiService {
    static String baseUrl = "https://test-repo-vercel-theta.vercel.app";
    static WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Content-Type", "application/json")
            .build();
    public static ArrayList<String> fetchImagesData(String formattedAddress){
        String requestBody = "{\"Listing\":\""+formattedAddress+"\"}";

        Flux<String> responseFlux = webClient.post()
                .uri("/ResideLibrary/Images")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToFlux(String.class);


        List<String> items = responseFlux.collectList().block();

        assert items!=null;
        String[] actualItems = new String[0];
        ObjectMapper objectMapper = new ObjectMapper();
        for(String item: items){
            try{
                actualItems = objectMapper.readValue(item, String[].class);
            }
            catch(Exception e){
                continue;
            }
        }
        return new ArrayList<String>(List.of(actualItems));
    }
}
