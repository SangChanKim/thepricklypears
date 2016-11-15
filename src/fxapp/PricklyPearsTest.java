package fxapp;


import model.Location;
import model.User;
import model.UserTitle;
import model.UserType;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.*;

/**
 * Created by sang on 11/13/16.
 */
public class PricklyPearsTest {

    private User u1;
    private User u2;
    private User u3;


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

    /**
     * Victor Chen's JUnits
     */

    @Before
    private void populateUsers() {
        u1 = new User("vchen36", "asdf");
        u1.setEmailAddress("myEmail@email.com");
        u1.setHomeAddress("123 Cookies Lane");
        u1.setName("Victor");
        u1.setUserTitle(UserTitle.DOCTOR);
        u1.setPhoneNumber("0001115555");
        u1.setUserType(UserType.USER);

        u2 = new User("vchen36", "asdf");
        u2.setEmailAddress("myEmail@email.com");
        u2.setHomeAddress("123 Cookies Lane");
        u2.setName("Victor");
        u2.setUserTitle(UserTitle.DOCTOR);
        u2.setPhoneNumber("0001115555");
        u2.setUserType(UserType.USER);

        u3 = new User("vchen36", "asdf");
        u3.setEmailAddress("myEmail@email.com");
        u3.setHomeAddress("123 Oreos Lane");
        u3.setName("Victor");
        u3.setUserTitle(UserTitle.DOCTOR);
        u3.setPhoneNumber("0001115555");
        u3.setUserType(UserType.USER);
    }

    @Test
    public void testUsersEquals() {
        assertTrue("User1 and User2 are equal", u1.equals(u2));

        assertFalse("User1 and User3 are not equal", u1.equals(u3));

        u3.setHomeAddress("123 Cookies Lane");

        assertTrue("User2 and User 3 are equal after User 3 moved", u2.equals(u3));

        assertTrue("Null check", u1.equals(null));
    }

}