package fxapp;


import model.Location;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by sang on 11/13/16.
 *
 * JUnit Test Cases
 */
public class PricklyPearsTest {


    /**
     * Sang-Chan Kim's JUnits
     */
    @Test
    public void testLocationEquals() {
        Location location1 = new Location(10.2, 15, "Georgia");
        Location location2 = new Location(10.2, 15, "Georgia");
        assertTrue("Are the two locations equal?", location1.equals(location2));

        Location location3 = new Location(10.2, 15, "George");
        assertFalse("Are the two locations false?", location1.equals(location3));

        assertFalse("Location is null, so equals should be false", location1.equals(null));
    }

}