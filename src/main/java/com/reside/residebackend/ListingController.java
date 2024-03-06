package com.reside.residebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class ListingController {
    @Autowired
    private ListingRepository listingRepository;

    //    @GetMapping("/getAll")
//    public List<Listing> getAllItems() {
//        return listingRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Listing getItemById(@PathVariable String id) {
//        return listingRepository.findById(id).orElse(null);
//    }
//
//    @PostMapping("/create")
//    public Item addItem(@RequestBody Item item) {
//        System.out.println("Create route running");
//        Item newItem = new Item(item.id, item.name, item.price);
//        itemRepository.save(newItem);
//        return item;
//    }
//
////    @PostMapping("/listings")
////    public List<Listing> addListings(@RequestBody String city, @RequestBody String state){
////
////    }
    @PostMapping("/testingParams")
    public ResponseEntity<String> addTest(@RequestParam String greeting, @RequestParam String name){
        System.out.println("Parameter 1: " + greeting);
        System.out.println("Parameter 2: " + name);

        return ResponseEntity.ok("Data retrieved successfully");
    }

    @GetMapping("/listings")
    public RentCastRentalListings getListings(@RequestParam String city, @RequestParam String state){
        RentCastRentalListings results = RentCastApiService.fetchListingData(city, state);

        for(int i =0; i < results.listings.size(); i++){
            Listing listing = new Listing(results.listings.get(i));
            listingRepository.save(listing);
        }
        return results;
    }

//    @PutMapping("/{id}")
//    public Item updateItem(@PathVariable String id, @RequestBody Item updatedItem) {
//        Item existingItem = itemRepository.findById(id).orElse(null);
//
//        if (existingItem != null) {
//            existingItem.setName(updatedItem.getName());
//            existingItem.setPrice(updatedItem.getPrice());
//            return itemRepository.save(existingItem);
//        } else {
//            return null;
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteItem(@PathVariable String id) {
//        itemRepository.deleteById(id);
//    }
}
