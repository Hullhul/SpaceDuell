package org.gradle;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class PersonTest {
    @Test
    public void canConstructAPersonWithAName() {
        Person person = new Person("Larry");
        assertEquals("Larry", person.getName());
        assertThat(person.getName()).isEqualTo("Larry");
    }
    
    @Test
    public void parseTest1() {
    	assertThat("  hallo  ".trim()).isEqualTo("hallo");
    	//assertEquals(true, 3 + 7 > 11);
    	assertThat(3 + 7).isGreaterThan(9);
    }
}
