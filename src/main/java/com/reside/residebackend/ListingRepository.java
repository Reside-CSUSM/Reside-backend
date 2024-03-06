package com.reside.residebackend;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ListingRepository extends MongoRepository<Listing, String> {

}
