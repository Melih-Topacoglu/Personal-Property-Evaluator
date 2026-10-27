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
            System.out.println("There is already a property with the id " + property.getId());
            return;
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
                .filter(p -> p.getDistrict() == district)
                .toList();
    }

    public boolean deleteById(int id) {
        return properties.removeIf(p -> p.getId() == id);
    }
}