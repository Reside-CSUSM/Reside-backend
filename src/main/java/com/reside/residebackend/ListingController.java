package com.reside.residebackend;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/listings")
public class ListingController {
    @Autowired
    private ListingRepository listingRepository;


    @PostMapping("/addUserFavoriteListing")
    public ResponseEntity<String> addFavoriteListing(@RequestParam String listingId, @RequestParam String userId){
        //Adds the user which 'favorited' to listing collection which contains seenBy array
        Listing listing = listingRepository.findListingById(listingId);
        
        if(listing == null){return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("listing doesn't exist with this id");}
        //Check if the userId doesn't exist in the array
        if(listing.getViewedBy().indexOf(userId) == -1){
            Boolean status = listing.getViewedBy().add(userId);
            listingRepository.save(listing);
            if(status == false){ResponseEntity.ok("couldn't add the userId to viewedBy");}
            else {return ResponseEntity.ok("added userId successfully");}
        }
        //Run this section if userId already exists within the array
        return ResponseEntity.ok("user already exists");
    }

    @PostMapping("/deleteUserFavoriteListing")
    public ResponseEntity<String> deleteFavoriteListing(@RequestParam String listingId, @RequestParam String userId) {
        //Deletes the user which 'favorited' the listing and deletes it within the collection in seenBy array
        
        Listing listing = listingRepository.findListingById(listingId);
        if(listing == null){return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("listing doesn't exist with this id");}
        //If the userId does exist in the array
        if(listing.getViewedBy().indexOf(userId) != -1){
            Boolean status =  listing.getViewedBy().remove(userId);
            listingRepository.save(listing);
            if(status == false){return ResponseEntity.ok("couldn't delete the userId in viewedBy");}
            else {return ResponseEntity.ok("userId deleted successfully");}
        }   

        //If the userId doesn't exist already
        return ResponseEntity.ok("user already doesn't exist");
    }
    
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
        System.out.print(city + ",");
        System.out.println(state);
        try{
            listingRepository.deleteListingsByBodyCityAndBodyState(city, state);
        } catch (Exception e){
            System.err.println("Error occurred during deletion: " + e.getMessage());
        }

        ArrayList<RentCastRentalListing> rentCastResults = RentCastApiService.fetchListingData(city, state);

        // for each rent cast rental get images, if there are images store the rental if not skip it
        System.out.println("Before");
        for(RentCastRentalListing rlisting: rentCastResults){
            System.out.println("Searching...");
            if(rlisting.formattedAddress != null && rlisting.bathrooms != 0){
                images = ImagesApiService.fetchImagesData(rlisting.formattedAddress);
                System.out.println("called the image api");
            }
            else{
                continue;
            }
            if(!images.isEmpty()){
                System.out.println("recieved images!");
                Listing newListing = new Listing(rlisting, images);
                listings.add(newListing);
                listingRepository.save(newListing);
            }
        }
        if(!listings.isEmpty()){
            System.out.println("Filled");
            return ResponseEntity.ok(listings);
        } else {
            System.out.println("Empty");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
}
