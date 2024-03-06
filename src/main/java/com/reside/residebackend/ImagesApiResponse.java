package com.reside.residebackend;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
class PayLoadObject{
    @JsonProperty("Images")
    ArrayList<String> Images;
    @JsonProperty("ListingIdentifier")
    String ListingIdentifier;
    @JsonProperty("MatchedWith")
    String MatchedWith;
}
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagesApiResponse {
    @JsonProperty("ErrorLog")
    public ArrayList<String> ErrorLog;

    @JsonProperty("Errors")
    public boolean Errors;

    @JsonProperty("RecipientPayload")
    public ArrayList<PayLoadObject> RecipientPayload;
}
