package com.reside.residebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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
        ArrayList<String> images = ImagesApiService.fetchImagesData("152 N Twin Oaks Valley Rd, San Marcos, CA 92069");
        return images;
    }
    @PostMapping("")
    public List<Listing> getListings(@RequestParam String city, @RequestParam String state){
        ArrayList<Listing> listings = new ArrayList<Listing>(); // used to output the listings found
        ArrayList<String> images = new ArrayList<String>(); // intialize images array

        ArrayList<RentCastRentalListing> rentCastResults = RentCastApiService.fetchListingData(city, state);

        // for each rent cast rental get images, if there are images store the rental if not skip it
        for(RentCastRentalListing rlisting: rentCastResults){
            if(rlisting.formattedAddress != null){
                images = ImagesApiService.fetchImagesData(rlisting.formattedAddress);
            }
            else{
                continue;
            }
            if(!images.isEmpty()){
                Listing newListing = new Listing(rlisting, images);
                listings.add(newListing);
                listingRepository.save(newListing);
            }
        }
        return listings;
    }
}
