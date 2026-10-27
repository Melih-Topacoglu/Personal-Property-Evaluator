import java.util.Optional;

import model.Property;
import repository.PropertyRepository;
import service.PropertyService;

public class Main {

    public static void main(String[] args) {

        // Dependency Injection
        PropertyRepository repository = new PropertyRepository();
        PropertyService service = new PropertyService(repository);

        // Create properties
        Property p1 = new Property(
                1, "Kadıköy", 4_500_000,
                120, 3, 500, 35, true);

        Property p2 = new Property(
                2, "Maltepe", 3_800_000,
                100, 2, 300, 45, true);

        Property p3 = new Property(
                3, "Üsküdar", 5_200_000,
                140, 4, 800, 25, false);

        // 1. Add properties
        service.addProperty(p1);
        service.addProperty(p2);
        service.addProperty(p3);

        // 2. List all properties
        System.out.println("\n--- ALL PROPERTIES ---");

        service.getAllProperties()
                .forEach(System.out::println);

        // 3. Find property by ID
        System.out.println("\n--- FIND PROPERTY BY ID: 2 ---");

        Optional<Property> result = service.getPropertyById(2);

        result.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Property not found"));

        // 4. Delete property
        System.out.println("\n--- DELETE PROPERTY BY ID: 1 ---");

        boolean deleted = service.deleteById(1);

        System.out.println("Deleted: " + deleted);

        // 5. List remaining properties
        System.out.println("\n--- REMAINING PROPERTIES ---");

        service.getAllProperties()
                .forEach(System.out::println);

        // 6. Try adding a duplicate ID
        System.out.println("\n--- DUPLICATE ID TEST ---");

        Property duplicate = new Property(
                2, "Ataşehir", 4_100_000,
                110, 3, 450, 30, true);

        service.addProperty(duplicate);

        // 7. Verify final property count
        System.out.println("\n--- FINAL PROPERTY COUNT ---");

        System.out.println(
                "Total properties: " + service.getAllProperties().size());
    }
}