package com.pragma.technology.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechnologyTest {
    @Test
    void testGettersAndSetters() {
        Technology tech = new Technology();
        tech.setId(1L);
        tech.setName("Java");
        tech.setDescription("Backend");

        assertEquals(1L, tech.getId());
        assertEquals("Java", tech.getName());
        assertEquals("Backend", tech.getDescription());
    }

    @Test
    void testAllArgsConstructor() {
        Technology tech = new Technology(2L, "Spring", "Framework");

        assertEquals(2L, tech.getId());
        assertEquals("Spring", tech.getName());
        assertEquals("Framework", tech.getDescription());
    }

}