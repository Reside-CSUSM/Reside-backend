package com.reside.residebackend;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "listings")
public class Listing {
    @Id
    private  String id;
    public RentCastRentalListing body;
    @Field("viewedBy")
    private List<String> viewedBy;
    @Field("images")
    private List<String> images;


    /**
     * Constructor for a new Listing Document
     * @param body The body of a rent cast request, the body itself is an object
     * @param images images found for the given listing
     */
    public Listing(RentCastRentalListing body, ArrayList<String> images){
        this.body = body;
        this.viewedBy = new ArrayList<String>(); // viewedBy will always start fresh for a new listing
        this.images = images;
    };

    public String getId(){
        return this.id;
    }
    public List<String> getViewedBy(){
        return this.viewedBy;
    }

    public List<String> getImages(){
        return this.images;
    }
}
