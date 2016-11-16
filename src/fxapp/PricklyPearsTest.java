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
 *
 * JUnit Test Cases
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

        Location location3 = new Location(10.2, 15, "george");
        assertFalse("Are the two same locations with different titles false?", location1.equals(location3));

        Location location4 = new Location(10.3, 15, "Georgia");
        assertFalse("Are these two same locations with different latitudes not equal?", location1.equals(location4));

        Location location5 = new Location(10.3, 16, "Georgia");
        assertFalse("Are these two same locations with different logitudes not equal?", location1.equals(location5));

        Location location6 = new Location(100, 500, "Florida");
        assertFalse("Are these two completely different locations with different attributes not equal?",
                location1.equals(location6));

        assertFalse("Location is null, so equals should be false", location1.equals(null));
    }

    /**
     * Victor Chen's JUnits
     */

    @Before
    public void populateUsers() {
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

        assertTrue("Null check", u1.equals(null));

        assertTrue("User1 and User2 are equal", u1.equals(u2));

        assertFalse("User1 and User3 are not equal", u1.equals(u3));

        u3.setHomeAddress("123 Cookies Lane");

        assertTrue("User2 and User 3 are equal after User 3 moved", u2.equals(u3));

        u2.setEmailAddress("curiousbob@email.com");

        assertFalse("User2 changed email address; User2 and User3 are not equal", u2.equals(u3));

        u3.setEmailAddress(u2.getEmailAddress());
        u3.setName("George");

        assertFalse("User3 changed email address but changed name; User2 and User3 are not equal", u2.equals(u3));

        u3.setName(u2.getName());
        u3.setUserType(UserType.MANAGER);

        assertFalse("User3 switched name back, but got a promotion; User2 and User 3 are not equal", u2.equals(u3));

        u2.setUserType(UserType.MANAGER);

        assertTrue("User2 also got a promotion; User2 and User3 are equal", u2.equals(u3));

        u2.setPhoneNumber("1112223333");

        assertFalse("User2 got a new phone; User2 and User3 are not equal", u2.equals(u3));

        u3.setPhoneNumber("1112223333");

        assertTrue("User3 also switched phones; User2 and User3 are equal again", u2.equals(u3));

        u2.setUserTitle(UserTitle.MISTER);

        assertFalse("User2 lost his degree; User2 and User3 are not equal",u2.equals(u3));

        u1.setUsername("vvvvvvvv");

        assertFalse("User1 changed his username; User1 and User3 are not the same", u1.equals(u3));

        u1.setUsername("vchen36");
        u1.setPassword("bestpassword2016");
        u3.setPassword("bestpassword2016");

        assertTrue("Just kidding, User1 changed passwords and so did User3", u1.equals(u3));


    }

}