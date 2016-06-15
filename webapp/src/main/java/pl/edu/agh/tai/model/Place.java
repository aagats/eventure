package pl.edu.agh.tai.model;


public class Place {

    private long id;
    private String name;
    private String city;
    private String street;
    private int buildingNumber;

    public Place(long id, String name, String city, String street, int buildingNumber) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
