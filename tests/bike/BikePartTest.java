package bike;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by gusty on 5/18/17.
 */
class BikePartTest {
    BikePart bp1 = new BikePart("saddle1", "1234567890", "45.49", "40.00", false);
    BikePart bp2 = new BikePart("saddle2", "1234567891", "55.49", "50.00", true);
    @Test
    void setSale() {
        bp1.setSale(true);
        assertEquals(true, bp1.getOnSale());
    }

    @Test
    void onSale() {
        assertEquals(false, bp1.getOnSale());
        assertEquals(true, bp2.getOnSale());
    }

    @Test
    void getSalePrice() {
        assertEquals("40.00", bp1.getSalePrice());
        assertEquals("50.00", bp2.getSalePrice());
    }

    @Test
    void setSalePrice() {
        bp1.setSalePrice("10.00");
        assertEquals("10.00", bp1.getSalePrice());
    }

    @Test
    void updatePriceAttributes() {
        BikePart bp = new BikePart("", "", "100.00", "75.00", false);
        bp2.updatePriceAttributes(bp);
        assertEquals("100.00", bp2.getPrice());
        assertEquals("75.00", bp2.getSalePrice());
        assertEquals(false, bp2.getOnSale());
    }

    /*
    @Test
    void equals() {
    }
    */

    /*
    @Test
    String toString() {
    }
    */

}