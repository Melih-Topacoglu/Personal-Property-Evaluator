package repository;

import model.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyRepository {
    private final List<Property> properties = new ArrayList<>();

    public void save(Property property) {
        if (properties.stream()
                .anyMatch(p -> p.getId() == property.getId())) {
            throw new IllegalArgumentException("Property with ID " + property.getId() + " already exists.");
        }
        properties.add(property);
    }

    public List<Property> findAll() {
        List<Property> allProperties = List.copyOf(properties);
        return allProperties;
    }

    public Optional<Property> findById(int id) {
        return properties.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public List<Property> findByDistrict(String district) {
        return properties.stream()
                .filter(p -> p.getDistrict().equalsIgnoreCase(district))
                .toList();
    }

    public boolean deleteById(int id) {
        return properties.removeIf(p -> p.getId() == id);
        
    }
}