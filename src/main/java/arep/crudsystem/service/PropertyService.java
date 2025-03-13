package arep.crudsystem.service;

import arep.crudsystem.model.Property;
import arep.crudsystem.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service class for managing properties.
 * This class contains business logic for CRUD operations, pagination, and search functionality.
 */
@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * Retrieves all properties with pagination.
     *
     * @param page The page number to retrieve (starting from 0).
     * @param size The number of properties per page.
     * @return A page of properties.
     */
    public Page<Property> getAllProperties(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return propertyRepository.findAll(pageable);
    }

    /**
     * Searches for properties based on filters and returns them with pagination.
     *
     * @param query    The search query to filter properties by address or description (optional).
     * @param maxPrice The maximum price to filter properties (optional).
     * @param maxSize  The maximum size to filter properties (optional).
     * @param page     The page number to retrieve (starting from 0).
     * @param size     The number of properties per page.
     * @return A page of properties matching the filters.
     */
    public Page<Property> searchProperties(String query, Double maxPrice, Double maxSize, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return propertyRepository.search(query, maxPrice, maxSize, pageable);
    }

    /**
     * Retrieves a property by its ID.
     *
     * @param id The ID of the property to retrieve.
     * @return An Optional containing the property if found, or empty if not found.
     */
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    /**
     * Creates a new property.
     *
     * @param property The property to create.
     * @return The created property.
     */
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    /**
     * Updates an existing property.
     *
     * @param id             The ID of the property to update.
     * @param propertyDetails The updated property details.
     * @return The updated property.
     * @throws RuntimeException If the property with the given ID is not found.
     */
    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));
        property.setAddress(propertyDetails.getAddress());
        property.setPrice(propertyDetails.getPrice());
        property.setSize(propertyDetails.getSize());
        property.setDescription(propertyDetails.getDescription());
        return propertyRepository.save(property);
    }

    /**
     * Deletes a property by its ID.
     *
     * @param id The ID of the property to delete.
     */
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}