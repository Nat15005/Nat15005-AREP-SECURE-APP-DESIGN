package arep.crudsystem.service;

import arep.crudsystem.model.Property;
import arep.crudsystem.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyService propertyService;

    @Test
    public void testCreateProperty() {
        Property property = new Property();
        property.setAddress("Calle 123");
        property.setPrice(100000.0);
        property.setSize(150.0);
        property.setDescription("Casa nueva");

        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property savedProperty = propertyService.createProperty(property);
        assertNotNull(savedProperty);
        assertEquals("Calle 123", savedProperty.getAddress());
    }

    @Test
    public void testGetAllProperties() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Property> properties = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(propertyRepository.findAll(pageable)).thenReturn(properties);

        Page<Property> result = propertyService.getAllProperties(0, 5);
        assertEquals(0, result.getTotalElements());
    }

    @Test
    public void testGetPropertyById() {
        Property property = new Property();
        property.setId(1L);
        property.setAddress("Calle 123");

        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));

        Optional<Property> result = propertyService.getPropertyById(1L);
        assertTrue(result.isPresent());
        assertEquals("Calle 123", result.get().getAddress());
    }

    @Test
    public void testUpdateProperty() {
        Property property = new Property();
        property.setId(1L);
        property.setAddress("Calle 123");

        Property updatedProperty = new Property();
        updatedProperty.setAddress("Calle 456");

        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
        when(propertyRepository.save(any(Property.class))).thenReturn(updatedProperty);

        Property result = propertyService.updateProperty(1L, updatedProperty);
        assertEquals("Calle 456", result.getAddress());
    }

    @Test
    public void testDeleteProperty() {
        doNothing().when(propertyRepository).deleteById(1L);
        propertyService.deleteProperty(1L);
        verify(propertyRepository, times(1)).deleteById(1L);
    }
}
