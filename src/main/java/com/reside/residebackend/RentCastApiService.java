package com.reside.residebackend;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RentCastApiService {
    public static String getStateCode(String state){
        Map <String, String> stateMap = new HashMap<String, String>(){{
            put("alabama", "AL");
            put("alaska", "AK");
            put("arizona", "AZ");
            put("arkansas", "AR");
            put("california", "CA");
            put("colorado", "CO");
            put("connecticut", "CT");
            put("delaware", "DE");
            put("florida", "FL");
            put("georgia", "GA");
            put("hawaii", "HI");
            put("idaho", "ID");
            put("illinois", "IL");
            put("indiana", "IN");
            put("iowa", "IA");
            put("kansas", "KS");
            put("kentucky", "KY");
            put("louisiana", "LA");
            put("maine", "ME");
            put("maryland", "MD");
            put("massachusetts", "MA");
            put("michigan", "MI");
            put("minnesota", "MN");
            put("mississippi", "MS");
            put("missouri", "MO");
            put("montana", "MO");
            put("nebraska", "NE");
            put("nevada", "NV");
            put("new hampshire", "NH");
            put("new jersey", "NJ");
            put("new mexico", "NM");
            put("new york", "NY");
            put("north carolina", "NC");
            put("north dakota", "ND");
            put("ohio", "OH");
            put("oregon", "OR");
            put("pennsylvania", "PA");
            put("rhode island", "RI");
            put("south carolina", "SC");
            put("south dakota", "SD");
            put("tennessee", "TN");
            put("texas", "TX");
            put("utah", "UT");
            put("vermont", "VT");
            put("virginia", "VA");
            put("washington", "WA");
            put("west virginia", "WV");
            put("wisconsin", "WI");
            put("wyoming", "WY");
        }};
        return stateMap.get(state.replace("+", " ").toLowerCase());
    }
    static String baseUrl = "https://api.rentcast.io/v1";
    private static final String RENTCASTAPIKEY = "08003ff919ef4dd99f1bfe3085598296"; // api key from https://app.rentcast.io/app/api
    static WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("X-Api-Key", RENTCASTAPIKEY)
            .build();

    public static ArrayList<RentCastRentalListing> fetchListingData(String city, String state){
        city = getStateCode(city);
        state = state.replace("+", " ");
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
