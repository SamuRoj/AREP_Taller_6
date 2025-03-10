package eci.arep.property.exception;

public class PropertyNotFound extends RuntimeException {
    public PropertyNotFound(Long id) {
        super("Property with id " + id + " doesn't exist.");
    }
}
