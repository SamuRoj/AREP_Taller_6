package eci.arep.property.controller;

import eci.arep.property.dto.PropertyDto;
import eci.arep.property.exception.PropertyNotFound;
import eci.arep.property.model.Property;
import eci.arep.property.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/properties")
@CrossOrigin(origins = "https://srapacheserver.duckdns.org", allowedHeaders = "*", allowCredentials = "true")
public class PropertyController {

    PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Property>> getAllProperties(){
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable long id){
        try {
            return ResponseEntity.ok(propertyService.getPropertyById(id));
        } catch (PropertyNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/address")
    public ResponseEntity<Iterable<Property>> filterByAddress(@RequestParam("address") String address){
        return ResponseEntity.ok(propertyService.filterPropertiesByAddress(address));
    }

    @GetMapping("/price")
    public ResponseEntity<Iterable<Property>> filterByPrice(@RequestParam("price") Double price){
        return ResponseEntity.ok(propertyService.filterPropertiesByPrice(price));
    }

    @GetMapping("/size")
    public ResponseEntity<Iterable<Property>> filterBySize(@RequestParam("size") Double size){
        return ResponseEntity.ok(propertyService.filterPropertiesBySize(size));
    }

    @PostMapping
    public ResponseEntity<String> createProperty(@RequestBody PropertyDto propertyDto){
        Property newProperty = propertyService.createProperty(propertyDto);
        URI uri = URI.create("/properties/" + newProperty.getId());
        return ResponseEntity.created(uri).body("/properties/" + newProperty.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id,
                                                   @RequestBody PropertyDto propertyDto){
        try {
            return ResponseEntity.ok(propertyService.updateProperty(id, propertyDto));
        } catch (PropertyNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id){
        try {
            propertyService.deleteProperty(id);
            return ResponseEntity.ok().build();
        } catch (PropertyNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }
}
