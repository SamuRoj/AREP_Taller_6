package eci.arep.property.dto;

public class PropertyDto {
    private String address;
    private Double price;
    private Double size;
    private String description;

    public PropertyDto() {
    }

    public PropertyDto(String address, Double price, Double size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
