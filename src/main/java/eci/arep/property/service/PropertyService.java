package eci.arep.property.service;

import eci.arep.property.dto.PropertyDto;
import eci.arep.property.exception.PropertyNotFound;
import eci.arep.property.model.Property;
import eci.arep.property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository){
        this.propertyRepository = propertyRepository;
    }

    public Iterable<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

    public Iterable<Property> filterPropertiesByAddress(String address){
        return propertyRepository.findByAddress(address);
    }

    public Iterable<Property> filterPropertiesByPrice(Double price){
        return propertyRepository.findByPrice(price);
    }

    public Iterable<Property> filterPropertiesBySize(Double size){
        return propertyRepository.findBySize(size);
    }

    public Property getPropertyById(long id) throws PropertyNotFound {
        if(propertyRepository.existsById(id)) return propertyRepository.findById(id);
        throw new PropertyNotFound(id);
    }

    public Property createProperty(PropertyDto propertyDto){
        Property newProperty = new Property(propertyDto);
        return propertyRepository.save(newProperty);
    }
    
    public Property updateProperty(Long id, PropertyDto propertyDto) throws PropertyNotFound{
        Property updatedProperty = getPropertyById(id);
        return propertyRepository.save(updatedProperty.updateProperty(propertyDto));
    }

    public void deleteProperty(Long id) throws PropertyNotFound {
        if(propertyRepository.existsById(id)) propertyRepository.deleteById(id);
        else throw new PropertyNotFound(id);
    }
}
