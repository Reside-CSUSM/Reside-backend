package com.reside.residebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ImagesApiService {
    private static WebClient webClient = null;

    @Autowired
    public ImagesApiService(WebClient.Builder webClientBuilder){
        webClient =  webClientBuilder.baseUrl("https://test-repo-vercel-theta.vercel.app").build();
    }

    public static Mono<ImagesApiResponse> fetchImagesData(String formattedAddress){
        String apiPath = "/ResideLibrary/Images";

        String requestBody = "{\"Listings\":[\"1464 La Linda Dr, San Marcos, CA 92078\"]}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return webClient.post()
                .uri(apiPath)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(ImagesApiResponse.class);
    }
}
