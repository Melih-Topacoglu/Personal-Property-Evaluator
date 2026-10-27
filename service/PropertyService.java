package service;

import java.util.List;
import java.util.Optional;

import model.Property;
import repository.PropertyRepository;

public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public void addProperty(Property property) {
        propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(int id) {
        return propertyRepository.findById(id);
    }

    public boolean deleteById(int id) {
        return propertyRepository.deleteById(id);
    }
}