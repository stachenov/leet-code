/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class HitCounterTest {

    private HitCounter hitCounter;
    
    @BeforeMethod
    public void setUp() {
        hitCounter = new HitCounter();
    }
    
    @Test
    public void hitCountIsInitiallyZero() {
        assertThat(hitCounter.getHits(0)).isZero();
    }
    
    @Test
    public void aHitIsKeptFor5Minutes() {
        hitCounter.hit(0);
        assertThat(hitCounter.getHits(299)).isEqualTo(1);
        assertThat(hitCounter.getHits(300)).isEqualTo(0);
    }
    
    @Test
    public void multipleHitsPerSecondAreCounted() {
        hitCounter.hit(0);
        hitCounter.hit(0);
        assertThat(hitCounter.getHits(299)).isEqualTo(2);
        assertThat(hitCounter.getHits(300)).isEqualTo(0);
    }
    
    @Test
    public void generalCase() {
        hitCounter.hit(0);
        hitCounter.hit(0);
        hitCounter.hit(1);
        hitCounter.hit(2);
        hitCounter.hit(2);
        hitCounter.hit(2);
        assertThat(hitCounter.getHits(299)).isEqualTo(6);
        assertThat(hitCounter.getHits(300)).isEqualTo(4);
        hitCounter.hit(300);
        assertThat(hitCounter.getHits(300)).isEqualTo(5);
        assertThat(hitCounter.getHits(301)).isEqualTo(4);
        assertThat(hitCounter.getHits(302)).isEqualTo(1);
        assertThat(hitCounter.getHits(303)).isEqualTo(1);
    }
    
}
