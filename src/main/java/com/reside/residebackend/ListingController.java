package com.reside.residebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/listings")
public class ListingController {
    @Autowired
    private ListingRepository listingRepository;
    @GetMapping("/getById")
    public ResponseEntity<Listing> getListingById(@RequestParam String id){
        Listing listing = listingRepository.findListingById(id);
        if(listing != null){
            return ResponseEntity.ok(listing);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/getByFormattedAddress")
    public ResponseEntity<Listing> getListingByFormattedAddress(@RequestParam String formattedAddress){
        Listing listing = listingRepository.findListingByBodyFormattedAddress(formattedAddress);
        if(listing != null){
            return ResponseEntity.ok(listing);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/getByAddress")
    public ResponseEntity<Listing> getListingByAddressLine1(@RequestParam String addressLine1){
        Listing listing = listingRepository.findListingByBodyAddressLine1(addressLine1);
        if(listing != null){
            return ResponseEntity.ok(listing);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/getByCityState")
    public ResponseEntity<List<Listing>> getListingsByCityState(@RequestParam String city, @RequestParam String state){

        List<Listing> listings = listingRepository.findListingsByBodyCityAndBodyState(city, state);
        if(!listings.isEmpty()){
            return ResponseEntity.ok(listings);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PostMapping("/createByCityState")
    public ResponseEntity<List<Listing>> getListings(@RequestParam String city, @RequestParam String state){
        ArrayList<Listing> listings = new ArrayList<Listing>(); // used to output the listings found
        ArrayList<String> images = new ArrayList<String>(); // intialize images array
        System.out.println(city);
        try{
            listingRepository.deleteListingsByBodyCityAndBodyState(city, state);
        } catch (Exception e){
            System.err.println("Error occurred during deletion: " + e.getMessage());
        }

        ArrayList<RentCastRentalListing> rentCastResults = RentCastApiService.fetchListingData(city, state);

        // for each rent cast rental get images, if there are images store the rental if not skip it
        for(RentCastRentalListing rlisting: rentCastResults){
            if(rlisting.formattedAddress != null && rlisting.bathrooms != 0){
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
        if(!listings.isEmpty()){
            return ResponseEntity.ok(listings);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
}
