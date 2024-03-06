package com.reside.residebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.http.HttpHeaders;
@Service
public class RentCastApiService {

    private static WebClient webClient = null;

    @Autowired
    public RentCastApiService(WebClient.Builder webClientBuilder){
        webClient = webClientBuilder.baseUrl("https://api.rentcast.io/v1/listings").build();
    }

    public static RentCastRentalListings fetchListingData(String city, String state){
        String apiPath = "/rental/long-term?city="+ city +"&state"+ state +"&status=Active&limit=5";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-Api-Key", "putApiKeyHere");
        // Make a Get request to RentCast api and return the result
        return webClient.get()
                .uri(apiPath)
                .headers(httpHeaders-> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(RentCastRentalListings.class)
                .block();
    }
}
