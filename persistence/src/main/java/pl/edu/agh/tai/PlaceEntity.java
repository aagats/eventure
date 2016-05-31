package pl.edu.agh.tai;

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
}