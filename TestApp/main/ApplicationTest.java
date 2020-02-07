package main;

import infrastructure.lhc.Building;
import infrastructure.lhc.Detector;
import infrastructure.lhc.LargeHadronCollider;
import infrastructure.lhc.Ring;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Order(1)
    @Test
    void test1() {
        LargeHadronCollider largeHadronCollider = new LargeHadronCollider();
        Ring ring = new Ring();
        largeHadronCollider.setRing(ring);

        assertNotNull(largeHadronCollider.getRing());
        assertNotNull(largeHadronCollider.getUsp01());
        assertNotNull(largeHadronCollider.getUsp02());

    }
    @Order(2)
    @Test
    void test2() {

        LargeHadronCollider largeHadronCollider = new LargeHadronCollider();
        Ring ring = new Ring();
        largeHadronCollider.setRing(ring);

        assertNotNull(largeHadronCollider.getUsp01().getBatteryArrayList());
        assertNotNull(largeHadronCollider.getUsp02().getBatteryArrayList());
        assertEquals(25, largeHadronCollider.getUsp01().getBatteryArrayList().size());
        assertEquals(25, largeHadronCollider.getUsp02().getBatteryArrayList().size());
    }
}