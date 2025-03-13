package arep.crudsystem.controller;

import arep.crudsystem.model.Property;
import arep.crudsystem.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;


    @Test
    public void testGetAllProperties() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(propertyController).build();

        Property property1 = new Property();
        property1.setId(1L);
        property1.setAddress("Calle 123");

        Property property2 = new Property();
        property2.setId(2L);
        property2.setAddress("Avenida 456");

        List<Property> properties = Arrays.asList(property1, property2);
        Page<Property> propertyPage = new PageImpl<>(properties, PageRequest.of(0, 5), properties.size());

        when(propertyService.getAllProperties(any(Integer.class), any(Integer.class))).thenReturn(propertyPage);

        mockMvc.perform(get("/api/properties")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateProperty() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(propertyController).build();

        Property property = new Property();
        property.setId(1L);
        property.setAddress("Calle 123");

        when(propertyService.createProperty(any(Property.class))).thenReturn(property);

        mockMvc.perform(post("/api/properties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Calle 123\",\"price\":100000,\"size\":150,\"description\":\"Casa nueva\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.address").value("Calle 123"));
    }

    @Test
    public void testGetPropertyById() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(propertyController).build();

        Property property = new Property();
        property.setId(1L);
        property.setAddress("Calle 123");

        when(propertyService.getPropertyById(1L)).thenReturn(Optional.of(property));

        mockMvc.perform(get("/api/properties/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.address").value("Calle 123"));
    }

    @Test
    public void testUpdateProperty() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(propertyController).build();

        Property property = new Property();
        property.setId(1L);
        property.setAddress("Calle 456");

        when(propertyService.updateProperty(any(Long.class), any(Property.class))).thenReturn(property);

        mockMvc.perform(put("/api/properties/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Calle 456\",\"price\":200000,\"size\":200,\"description\":\"Casa grande\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.address").value("Calle 456"));
    }

    @Test
    public void testDeleteProperty() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(propertyController).build();

        mockMvc.perform(delete("/api/properties/1"))
                .andExpect(status().isNoContent());
    }
}
