package com.reside.residebackend;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ListingRepository extends MongoRepository<Listing, String> {
    Listing findListingByBodyFormattedAddress(String formattedAddress);
    Listing findListingByBodyAddressLine1(String addressLine1);
    Listing findListingById(String id);
    List<Listing> findListingsByBodyCityAndBodyState(String city, String state);

    void deleteListingsByBodyCityAndBodyState(String city, String state);

}
