package fxapp;


import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

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
     * Michael Wang's JUnits
     */

    @Test
    public void testWaterSourceReportEquals() {
        WaterSourceReport report1 = new WaterSourceReport("root", 1, new Date(1000000),
                new Location(10.2, 15, "Georgia"), WaterType.BOTTLED, WaterCondition.POTABLE);

        WaterSourceReport report2 = new WaterSourceReport("root", 1, new Date(1000000),
                new Location(10.2, 15, "Georgia"), WaterType.BOTTLED, WaterCondition.POTABLE);

        assertTrue("These should be equal.", report1.equals(report2));

        report2.setUsername("notroot");
        assertFalse("Reports with different usernames should not be equal.", report1.equals(report2));
        report2.setUsername("root");

        report2.setReportNumber(2);
        assertFalse("Reports with different report numbers should not be equal.", report1.equals(report2));
        report2.setReportNumber(1);

        report2.setDate(new Date(2000000));
        assertFalse("Reports with different dates should not be equal.", report1.equals(report2));
        report2.setDate(new Date(1000000));

        report2.setLocation(new Location(10, 10, "notGeorgia"));
        assertFalse("Reports with different locations should not be equal.", report1.equals(report2));
        report2.setLocation(new Location(10.2, 15, "Georgia"));

        report2.setWaterType(WaterType.LAKE);
        assertFalse("Reports with different water types should not be equal.", report1.equals(report2));
        report2.setWaterType(WaterType.BOTTLED);

        report2.setWaterCondition(WaterCondition.TREATABLE_CLEAR);
        assertFalse("Reports with different water conditions should not be equal.", report1.equals(report2));
        report2.setWaterCondition(WaterCondition.POTABLE);

        assertFalse("return false on null water report", report1.equals(null));

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




    /**
     * Valerie Avva's JUnits
     */
    @Test
    public void testWaterQualityReportEquals() {
        Date now = new Date();
        WaterQualityReport r1 = new WaterQualityReport("val", 0, now,
                new Location(42.0, 42.0, "Mordor"), QualityCondition.SAFE, 36, 58);
        WaterQualityReport r2 = new WaterQualityReport("val", 0, now,
                new Location(42.0, 42.0, "Mordor"), QualityCondition.SAFE, 36, 58);

        assertTrue("Two water reports with the same data are the same", r1.equals(r2));

        r1.setUsername("root");
        assertFalse("Reports with different usernames should return false", r1.equals(r2));
        r1.setUsername("val");

        r1.setReportNumber(23);
        assertFalse("Reports with different report numbers should return false", r1.equals(r2));
        r1.setReportNumber(0);

        //Date temp = new Date(2015, 3, 23);
        Date temp = new Date(2000000);
        r1.setDate(temp);
        assertFalse("Reports with different dates should return false", r1.equals(r2));
        r1.setDate(now);

        r1.setLocation(new Location(52.0, 16.0, "The Shire"));
        assertFalse("Reports with different locations should return false", r1.equals(r2));
        r1.setLocation(new Location(42.0, 42.0, "Mordor"));

        r1.setWaterCondition(QualityCondition.UNSAFE);
        assertFalse("Reports with different water conditions should return false", r1.equals(r2));
        r1.setWaterCondition(QualityCondition.SAFE);

        r1.setVirusPPM(23);
        assertFalse("Reports with different Virus PPMs should return false", r1.equals(r2));
        r1.setVirusPPM(36);

        r1.setContaminantPPM(98);
        assertFalse("Reports with different Contaminant PPMs should return false", r1.equals(r2));
        r1.setContaminantPPM(58);

        assertFalse("null reports should return false", r1.equals(null));

    }

}