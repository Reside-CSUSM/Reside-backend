package com.reside.residebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Flux;


@Service
public class RentCastApiService {

    private static WebClient webClient = null;
    private static String RENTCASTAPIKEY = "08003ff919ef4dd99f1bfe3085598296"; // api key from https://app.rentcast.io/app/api

    @Autowired
    public RentCastApiService(WebClient.Builder webClientBuilder){
        webClient = webClientBuilder.baseUrl("https://api.rentcast.io/v1/listings").build();
    }

    public static Flux<RentCastRentalListing> fetchListingData(String city, String state){
        String apiPath = "/rental/long-term?city="+ city +"&state="+ state +"&status=Active&limit=499";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-Api-Key", RENTCASTAPIKEY);
        // Make a Get request to RentCast api and return the result
        return webClient.get()
                .uri(apiPath)
                .headers(httpHeaders-> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToFlux(RentCastRentalListing.class);
    }

}
