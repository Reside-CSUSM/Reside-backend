package com.reside.residebackend;

public class RentCastRentalListing {
    public String formattedAddress;
    public String addressLine1;
    public String addressLine2;
    public String city;
    public String state;
    public String zipCode;
    public String county;
    public double latitude;
    public double longitude;
    public String propertyType;
    public double bedrooms;
    public double bathrooms;
    public double squareFootage;
    public double lotSize;
    public int yearBuilt;
    public String status;
    public double price;
    public String listedDate;
    public String removedDate;
    public String createdDate;
    public String lastSeenDate;
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
