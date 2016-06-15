package pl.edu.agh.tai.persistence.entitites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlaceEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String city;
    private String street;
    private int buildingNumber;

    public PlaceEntity() {
    }

    public PlaceEntity(String name, String city, String street, int buildingNumber) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public long getId() {
        return id;
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