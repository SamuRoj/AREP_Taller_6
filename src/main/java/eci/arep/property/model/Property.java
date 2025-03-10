package eci.arep.property.model;

import eci.arep.property.dto.PropertyDto;
import jakarta.persistence.*;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String address;
    private Double price;
    private Double size;
    private String description;

    public Property() {
    }

    public Property(Long id, String address, Double price, Double size, String description) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    public Property(String address, Double price, Double size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    public Property(PropertyDto propertyDto) {
        this.address = propertyDto.getAddress();
        this.price = propertyDto.getPrice();
        this.size = propertyDto.getSize();
        this.description = propertyDto.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Property updateProperty(PropertyDto propertyDto){
        this.address = propertyDto.getAddress() != null ? propertyDto.getAddress() : this.address;
        this.price = propertyDto.getPrice() != null ? propertyDto.getPrice() : this.price;
        this.size = propertyDto.getSize() != null ? propertyDto.getSize() : this.size;
        this.description = propertyDto.getDescription() != null ? propertyDto.getDescription() : this.description;
        return this;
    }
}
