package com.delta.server.api.data;

import com.google.gson.annotations.SerializedName;

public class LocationDTO {
    private final String country;
    private final String name;
    @SerializedName("short")
    private final String shortName;

    public LocationDTO(String country, String name, String shortName) {
        this.country = country;
        this.name = name;
        this.shortName = shortName;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}
