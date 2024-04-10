package com.reside.residebackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class RentCastApiService {
    static String baseUrl = "https://api.rentcast.io/v1";
    private static final String RENTCASTAPIKEY = "08003ff919ef4dd99f1bfe3085598296"; // api key from https://app.rentcast.io/app/api
    static WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("X-Api-Key", RENTCASTAPIKEY)
            .build();

    public static ArrayList<RentCastRentalListing> fetchListingData(String city, String state){
        String apiPath = "/listings/rental/long-term?city="+ city +"&state="+ state +"&status=Active&limit=499";

        Flux<RentCastRentalListing> fluxResponse = webClient.get()
                .uri(apiPath)
                .retrieve()
                .bodyToFlux(RentCastRentalListing.class);

        List<RentCastRentalListing> items = fluxResponse.collectList().block();

        assert items != null;
        return new ArrayList<RentCastRentalListing>(items);
    }

}
