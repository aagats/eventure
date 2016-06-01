package pl.edu.agh.tai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceDto {
    private final String name;
    private final String city;
    private final String street;
    private final int buildingNumber;

    @JsonCreator
    public PlaceDto(@JsonProperty(value = "name", required = true) String name,
                    @JsonProperty("city") String city,
                    @JsonProperty("street") String street,
                    @JsonProperty ("buildingNumber") int buildingNumber) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }
}