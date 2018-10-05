
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;



//https://www.census.gov/srd/www/genhol/easter500.html
public class EasterJUnitTest {

//    public EasterJUnitTest(){}
    @Test
    public void testPreviousEasterDates(){

        //10 Tests before 2018
        Easter e1600 = new Easter(1600);
        Easter e1610 = new Easter(1610);
        Easter e1620 = new Easter(1620);
        Easter e1660 = new Easter(1660);
        Easter e1700 = new Easter(1700);
        Easter e1770 = new Easter(1770);
        Easter e1800 = new Easter(1800);
        Easter e1900 = new Easter(1900);
        Easter e2000 = new Easter(2000);
        Easter e2017 = new Easter(2017);

        assertEquals("Easter: April 2, 1600",e1600.toString());
        assertEquals("Easter: April 11, 1610",e1610.toString());
        assertEquals("Easter: April 19, 1620",e1620.toString());
        assertEquals("Easter: March 28, 1660",e1660.toString());
        assertEquals("Easter: April 11, 1700",e1700.toString());
        assertEquals("Easter: April 15, 1770",e1770.toString());
        assertEquals("Easter: April 13, 1800",e1800.toString());
        assertEquals("Easter: April 15, 1900",e1900.toString());
        assertEquals("Easter: April 23, 2000",e2000.toString());
        assertEquals("Easter: April 16, 2017",e2017.toString());

    }

    @Test
    public void testFutureEasterDates() {
            //10 Tests after 2018
            Easter e2020 = new Easter(2020);
            Easter e2035 = new Easter(2035);
            Easter e2045 = new Easter(2045);
            Easter e2055 = new Easter(2055);
            Easter e2065 = new Easter(2065);
            Easter e2075 = new Easter(2075);
            Easter e2085 = new Easter(2085);
            Easter e2095 = new Easter(2095);
            Easter e2096 = new Easter(2096);
            Easter e2099 = new Easter(2099);

            assertEquals("Easter: April 12, 2020", e2020.toString());
            assertEquals("Easter: March 25, 2035", e2035.toString());
            assertEquals("Easter: April 9, 2045", e2045.toString());
            assertEquals("Easter: April 18, 2055", e2055.toString());
            assertEquals("Easter: March 29, 2065", e2065.toString());
            assertEquals("Easter: April 7, 2075", e2075.toString());
            assertEquals("Easter: April 15, 2085", e2085.toString());
            assertEquals("Easter: April 24, 2095", e2095.toString());
            assertEquals("Easter: April 15, 2096", e2096.toString());
            assertEquals("Easter: April 12, 2099", e2099.toString());


    }
}
