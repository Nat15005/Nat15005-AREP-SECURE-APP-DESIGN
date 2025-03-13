package arep.crudsystem.repository;

import arep.crudsystem.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing properties.
 * This interface extends JpaRepository to provide CRUD operations
 * and custom query methods for the Property entity.
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    /**
     * Searches for properties based on filters and returns them with pagination.
     *
     * @param query    The search query to filter properties by address or description (optional).
     * @param maxPrice The maximum price to filter properties (optional).
     * @param maxSize  The maximum size to filter properties (optional).
     * @param pageable The pagination information (page number, page size, etc.).
     * @return A page of properties matching the filters.
     */
    @Query("SELECT p FROM Property p WHERE " +
            "(:query IS NULL OR p.address LIKE %:query% OR p.description LIKE %:query%) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:maxSize IS NULL OR p.size <= :maxSize)")
    Page<Property> search(@Param("query") String query,
                          @Param("maxPrice") Double maxPrice,
                          @Param("maxSize") Double maxSize,
                          Pageable pageable);
}
