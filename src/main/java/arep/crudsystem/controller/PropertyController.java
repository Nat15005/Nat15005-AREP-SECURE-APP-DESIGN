package arep.crudsystem.controller;

import arep.crudsystem.model.Property;
import arep.crudsystem.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing properties.
 * This controller provides endpoints for CRUD operations on properties,
 * including pagination and search functionality.
 */
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    /**
     * Retrieves all properties with pagination.
     *
     * @param page The page number to retrieve (default is 0).
     * @param size The number of properties per page (default is 5).
     * @return A page of properties.
     */
    @GetMapping
    public Page<Property> getAllProperties(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size) {
        return propertyService.getAllProperties(page, size);
    }

    /**
     * Searches for properties based on filters and returns them with pagination.
     *
     * @param query    The search query to filter properties by address or description (optional).
     * @param maxPrice The maximum price to filter properties (optional).
     * @param maxSize  The maximum size to filter properties (optional).
     * @param page     The page number to retrieve (default is 0).
     * @param size     The number of properties per page (default is 5).
     * @return A page of properties matching the filters.
     */
    @GetMapping("/search")
    public Page<Property> searchProperties(@RequestParam(required = false) String query,
                                           @RequestParam(required = false) Double maxPrice,
                                           @RequestParam(required = false) Double maxSize,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size) {
        return propertyService.searchProperties(query, maxPrice, maxSize, page, size);
    }

    /**
     * Retrieves a property by its ID.
     *
     * @param id The ID of the property to retrieve.
     * @return A ResponseEntity containing the property if found, or a 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new property.
     *
     * @param property The property to create.
     * @return The created property.
     */
    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }

    /**
     * Updates an existing property.
     *
     * @param id             The ID of the property to update.
     * @param propertyDetails The updated property details.
     * @return A ResponseEntity containing the updated property.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) {
        return ResponseEntity.ok(propertyService.updateProperty(id, propertyDetails));
    }

    /**
     * Deletes a property by its ID.
     *
     * @param id The ID of the property to delete.
     * @return A ResponseEntity with no content (204 status).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}