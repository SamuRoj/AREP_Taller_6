package eci.arep.property.repository;

import eci.arep.property.model.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {
    Property findById(long id);
    List<Property> findByAddress(String address);
    List<Property> findByPrice(Double price);
    List<Property> findBySize(Double size);
}
