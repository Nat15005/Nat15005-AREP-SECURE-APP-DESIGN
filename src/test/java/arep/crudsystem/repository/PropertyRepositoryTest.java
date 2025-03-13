package arep.crudsystem.repository;

import arep.crudsystem.model.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PropertyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PropertyRepository propertyRepository;

    private Property property1;
    private Property property2;

    @BeforeEach
    public void setUp() {

        property1 = new Property();
        property1.setAddress("Calle 123");
        property1.setPrice(100000.0);
        property1.setSize(150.0);
        property1.setDescription("Casa en el centro");
        entityManager.persist(property1);

        property2 = new Property();
        property2.setAddress("Avenida 456");
        property2.setPrice(200000.0);
        property2.setSize(200.0);
        property2.setDescription("Apartamento con vista al mar");
        entityManager.persist(property2);

        entityManager.flush();
    }

    @Test
    public void testFindAll() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Property> properties = propertyRepository.findAll(pageable);

        assertEquals(2, properties.getTotalElements());
        assertTrue(properties.getContent().contains(property1));
        assertTrue(properties.getContent().contains(property2));
    }

    @Test
    public void testSearchByQuery() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Property> properties = propertyRepository.search("centro", null, null, pageable);

        assertEquals(1, properties.getTotalElements());
        assertEquals("Calle 123", properties.getContent().get(0).getAddress());
    }

    @Test
    public void testSearchByMaxPrice() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Property> properties = propertyRepository.search(null, 150000.0, null, pageable);

        assertEquals(1, properties.getTotalElements());
        assertEquals("Calle 123", properties.getContent().get(0).getAddress());
    }

    @Test
    public void testSearchByMaxSize() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Property> properties = propertyRepository.search(null, null, 180.0, pageable);

        assertEquals(1, properties.getTotalElements());
        assertEquals("Calle 123", properties.getContent().get(0).getAddress());
    }

    @Test
    public void testFindById() {
        Optional<Property> foundProperty = propertyRepository.findById(property1.getId());

        assertTrue(foundProperty.isPresent());
        assertEquals("Calle 123", foundProperty.get().getAddress());
    }

    @Test
    public void testSaveProperty() {
        Property newProperty = new Property();
        newProperty.setAddress("Calle 789");
        newProperty.setPrice(300000.0);
        newProperty.setSize(250.0);
        newProperty.setDescription("Casa en las afueras");

        Property savedProperty = propertyRepository.save(newProperty);

        assertNotNull(savedProperty.getId());
        assertEquals("Calle 789", savedProperty.getAddress());
    }

    @Test
    public void testDeleteProperty() {
        propertyRepository.deleteById(property1.getId());

        Optional<Property> deletedProperty = propertyRepository.findById(property1.getId());
        assertFalse(deletedProperty.isPresent());
    }
}