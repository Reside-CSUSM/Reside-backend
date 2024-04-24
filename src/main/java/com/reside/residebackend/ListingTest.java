package com.reside.residebackend;

public class ListingTest {
    
    public static void main(String[] args){
        String city = "San Marcos";
        String state = "CA";
        ArrayList<RentCastRentalListing> rentCastResults = RentCastApiService.fetchListingData(city, state);
        System.out.println(rentCastResults)
    }
}
