package com.reside.residebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/listings")
public class ListingController {
    @Autowired
    private ListingRepository listingRepository;
    @GetMapping("/getAll")
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    @GetMapping("/getById")
    public Optional<Listing> getListingById(@RequestParam String id){
        return listingRepository.findById(id);
    }

    @PostMapping("/testingImagesCall")
    public ArrayList<String> getImages(){
        AtomicReference<ArrayList<String>> images = new AtomicReference<>(new ArrayList<String>());
        Mono<ImagesApiResponse> mono = ImagesApiService.fetchImagesData("1464 La Linda Dr, San Marcos, CA 92078");
        mono.subscribe(
                response-> {
                    List<PayLoadObject> recipientPayloads = response.RecipientPayload;
                    images.set(recipientPayloads.getFirst().Images);
                }
        );

        System.out.println(images.get().isEmpty() + " This ran");
        return images.get();
    }
    @PostMapping("/")
    public List<Listing> getListings(@RequestParam String city, @RequestParam String state){
        Flux<RentCastRentalListing> results = RentCastApiService.fetchListingData(city, state);

        ArrayList<Listing> listings = new ArrayList<Listing>(); // used to output the listings found
        AtomicReference<ArrayList<String>> images = new AtomicReference<>(new ArrayList<String>());

        results.publishOn(Schedulers.boundedElastic()).doOnNext(rentCastListing->{
            Mono<ImagesApiResponse> mono = ImagesApiService.fetchImagesData(rentCastListing.formattedAddress);
            mono.subscribe(
                    response-> {
                        List<PayLoadObject> recipientPayloads = response.RecipientPayload;
                        images.set(recipientPayloads.getFirst().Images);
                    }
            );

            if(!images.get().isEmpty()){
                Listing listing = new Listing(rentCastListing, images.get());
                listings.add(listing);
                listingRepository.save(listing);
            }
        }).subscribe();
        return listings;
    }
}
