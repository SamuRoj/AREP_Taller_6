package eci.arep.property.service;

import eci.arep.property.dto.PropertyDto;
import eci.arep.property.exception.PropertyNotFound;
import eci.arep.property.model.Property;
import eci.arep.property.repository.PropertyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTests {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyService propertyService;

    private Property property1;
    private Property property2;

    @BeforeEach
    void setup(){
        property1 = new Property(
    "Cra 100", 200000.0, 100.0, "Casa Nueva"
        );

        property2 = new Property(
    "Cra 90", 150000.0, 200.0, "Edificio"
        );
    }

    @Test
    void PropertyService_getAllProperties(){
        List<Property> properties = new ArrayList<>();
        properties.add(property1);
        properties.add(property2);

        when(propertyRepository.findAll()).thenReturn(properties);

        List<Property> propertiesRetrieved = (List<Property>) propertyService.getAllProperties();

        Assertions.assertNotNull(propertiesRetrieved);
        Assertions.assertEquals(2, propertiesRetrieved.size());
    }

    @Test
    void PropertyService_filterPropertiesByAddress() {
        List<Property> property1List = new ArrayList<>();
        property1List.add(property1);
        List<Property> property2List = new ArrayList<>();
        property2List.add(property2);

        when(propertyRepository.findByAddress("Cra 100")).thenReturn(property1List);

        List<Property> property1Retrieved = (List<Property>) propertyService.filterPropertiesByAddress("Cra 100");

        Assertions.assertNotNull(property1Retrieved);
        Assertions.assertEquals("Cra 100", property1Retrieved.get(0).getAddress());
        Assertions.assertEquals(200000.0, property1Retrieved.get(0).getPrice());
        Assertions.assertEquals(100.0, property1Retrieved.get(0).getSize());
        Assertions.assertEquals("Casa Nueva", property1Retrieved.get(0).getDescription());

        when(propertyRepository.findByAddress("Cra 90")).thenReturn(property2List);

        List<Property> property2Retrieved = (List<Property>) propertyService.filterPropertiesByAddress("Cra 90");

        Assertions.assertNotNull(property2Retrieved);
        Assertions.assertEquals("Cra 90", property2Retrieved.get(0).getAddress());
        Assertions.assertEquals(150000.0, property2Retrieved.get(0).getPrice());
        Assertions.assertEquals(200.0, property2Retrieved.get(0).getSize());
        Assertions.assertEquals("Edificio", property2Retrieved.get(0).getDescription());
    }

    @Test
    void PropertyService_filterPropertiesByPrice() {
        List<Property> property1List = new ArrayList<>();
        property1List.add(property1);
        List<Property> property2List = new ArrayList<>();
        property2List.add(property2);

        when(propertyRepository.findByPrice(200000.0)).thenReturn(property1List);

        List<Property> property1Retrieved = (List<Property>) propertyService.filterPropertiesByPrice(200000.0);

        Assertions.assertNotNull(property1Retrieved);
        Assertions.assertEquals("Cra 100", property1Retrieved.get(0).getAddress());
        Assertions.assertEquals(200000.0, property1Retrieved.get(0).getPrice());
        Assertions.assertEquals(100.0, property1Retrieved.get(0).getSize());
        Assertions.assertEquals("Casa Nueva", property1Retrieved.get(0).getDescription());

        when(propertyRepository.findByPrice(150000.0)).thenReturn(property2List);

        List<Property> property2Retrieved = (List<Property>) propertyService.filterPropertiesByPrice(150000.0);

        Assertions.assertNotNull(property2Retrieved);
        Assertions.assertEquals("Cra 90", property2Retrieved.get(0).getAddress());
        Assertions.assertEquals(150000.0, property2Retrieved.get(0).getPrice());
        Assertions.assertEquals(200.0, property2Retrieved.get(0).getSize());
        Assertions.assertEquals("Edificio", property2Retrieved.get(0).getDescription());
    }

    @Test
    void PropertyService_filterPropertiesBySize() {
        List<Property> property1List = new ArrayList<>();
        property1List.add(property1);
        List<Property> property2List = new ArrayList<>();
        property2List.add(property2);

        when(propertyRepository.findBySize(100.0)).thenReturn(property1List);

        List<Property> property1Retrieved = (List<Property>) propertyService.filterPropertiesBySize(100.0);

        Assertions.assertNotNull(property1Retrieved);
        Assertions.assertEquals("Cra 100", property1Retrieved.get(0).getAddress());
        Assertions.assertEquals(200000.0, property1Retrieved.get(0).getPrice());
        Assertions.assertEquals(100.0, property1Retrieved.get(0).getSize());
        Assertions.assertEquals("Casa Nueva", property1Retrieved.get(0).getDescription());

        when(propertyRepository.findBySize(200.0)).thenReturn(property2List);

        List<Property> property2Retrieved = (List<Property>) propertyService.filterPropertiesBySize(200.0);

        Assertions.assertNotNull(property2Retrieved);
        Assertions.assertEquals("Cra 90", property2Retrieved.get(0).getAddress());
        Assertions.assertEquals(150000.0, property2Retrieved.get(0).getPrice());
        Assertions.assertEquals(200.0, property2Retrieved.get(0).getSize());
        Assertions.assertEquals("Edificio", property2Retrieved.get(0).getDescription());
    }

    @Test
    void PropertyService_getPropertyById() {
        when(propertyRepository.existsById(1L)).thenReturn(true);
        when(propertyRepository.findById(1L)).thenReturn(property1);

        Property property = propertyService.getPropertyById(1L);

        Assertions.assertNotNull(property);
        Assertions.assertEquals("Cra 100", property.getAddress());
        Assertions.assertEquals(200000.0, property.getPrice());
        Assertions.assertEquals(100.0, property.getSize());
        Assertions.assertEquals("Casa Nueva", property.getDescription());
    }

    @Test
    void PropertyService_getPropertyById_ThrowsNotFound() {
        when(propertyRepository.existsById(1L)).thenReturn(false);

        Assertions.assertThrows(PropertyNotFound.class,
                () -> propertyService.getPropertyById(1L),
                "Property with id 1 doesn't exist.");
    }

    @Test
    void PropertyService_createProperty() {
        PropertyDto propertyDto = new PropertyDto(
    "Cra 100", 200000.0, 100.0, "Casa Nueva"
        );

        when(propertyRepository.save(Mockito.any(Property.class))).thenReturn(property1);

        Property propertySaved = propertyService.createProperty(propertyDto);

        Assertions.assertNotNull(propertySaved);
        Assertions.assertEquals("Cra 100", propertySaved.getAddress());
        Assertions.assertEquals(200000.0, propertySaved.getPrice());
        Assertions.assertEquals(100.0, propertySaved.getSize());
        Assertions.assertEquals("Casa Nueva", propertySaved.getDescription());
    }

    @Test
    void PropertyService_updateProperty() {
        PropertyDto propertyDto = new PropertyDto(
                "Cra 85", 40000.0, 40.0, "Casa"
        );

        when(propertyRepository.existsById(1L)).thenReturn(true);
        when(propertyRepository.findById(1L)).thenReturn(property1);
        when(propertyRepository.save(Mockito.any(Property.class))).thenReturn(property1);

        Property propertyUpdated = propertyService.updateProperty(1L, propertyDto);

        Assertions.assertNotNull(propertyUpdated);
        Assertions.assertEquals("Cra 85", propertyUpdated.getAddress());
        Assertions.assertEquals(40000.0, propertyUpdated.getPrice());
        Assertions.assertEquals(40.0, propertyUpdated.getSize());
        Assertions.assertEquals("Casa", propertyUpdated.getDescription());
    }

    @Test
    void PropertyService_deleteProperty_ThrowsNotFound() {
        when(propertyRepository.existsById(1L)).thenReturn(false);

        Assertions.assertThrows(PropertyNotFound.class,
                () -> propertyService.getPropertyById(1L),
                "Property with id 1 doesn't exist.");
    }
}
