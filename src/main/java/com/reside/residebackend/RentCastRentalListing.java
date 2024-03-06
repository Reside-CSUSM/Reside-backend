package com.reside.residebackend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RentCastRentalListing {
    @JsonProperty("formattedAddress")
    public String formattedAddress;
    @JsonProperty("addressLine1")
    public String addressLine1;
    @JsonProperty("addressLine2")
    public String addressLine2;
    @JsonProperty("city")
    public String city;
    @JsonProperty("state")
    public String state;
    @JsonProperty("zipCode")
    public String zipCode;
    @JsonProperty("county")
    public String county;
    @JsonProperty("latitude")
    public double latitude;
    @JsonProperty("longitude")
    public double longitude;
    @JsonProperty("propertyType")
    public String propertyType;
    @JsonProperty("bedrooms")
    public double bedrooms;
    @JsonProperty("bathrooms")
    public double bathrooms;
    @JsonProperty("squareFootage")
    public double squareFootage;
    @JsonProperty("lotSize")
    public double lotSize;
    @JsonProperty("yearBuilt")
    public int yearBuilt;
    @JsonProperty("status")
    public String status;
    @JsonProperty("price")
    public double price;
    @JsonProperty("listedDate")
    public String listedDate;
    @JsonProperty("removedDate")
    public String removedDate;
    @JsonProperty("createdDate")
    public String createdDate;
    @JsonProperty("lastSeenDate")
    public String lastSeenDate;
    @JsonProperty("daysOnMarket")
    public int daysOnMarket;

    public RentCastRentalListing(String formattedAddress, String addressLine1, String addressLine2, String city, String state, String zipCode, String county, double latitude, double longitude, String propertyType, double bedrooms, double bathrooms, double squareFootage, double lotSize, int yearBuilt, String status, double price, String listedDate, String removedDate, String createdDate, String lastSeenDate, int daysOnMarket){
        this.formattedAddress = formattedAddress;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.county = county;
        this.latitude = latitude;
        this.longitude = longitude;
        this.propertyType = propertyType;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squareFootage = squareFootage;
        this.lotSize = lotSize;
        this.yearBuilt = yearBuilt;
        this.status = status;
        this.price = price;
        this.listedDate = listedDate;
        this.removedDate = removedDate;
        this.createdDate = createdDate;
        this.lastSeenDate = lastSeenDate;
        this.daysOnMarket = daysOnMarket;
    }
}