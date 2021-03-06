import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LFUCacheTest {
    private LFUCache<Integer, String> cache;

    @BeforeEach
    public void testSetCapacity() {
        cache = new LFUCache<>(10);
    }

    @Test
    public void testAddCacheObject() {
        cache.add(1, "test");
    }

    @Test
    public void testGetCacheObject() {
        cache.add(1, "test");

        assertEquals("test", cache.get(1));
    }

    @Test
    public void testCapacity() {
        for (int i = 0; i < 11; i++) {
            cache.add(i, "test" + i);
        }

        assertNull(cache.get(0));
    }

    @Test
    public void testLFUCache() {
        for (int i = 0; i < 10; i++) {
            cache.add(i, "test" + i);
        }

        for (int i = 0; i < 8; i++) {
            assertEquals("test" + i, cache.get(i));
        }

        cache.add(10, "test10");

        assertNull(cache.get(8));
        assertEquals("test10", cache.get(10));

        cache.add(11, "test11");

        assertNull(cache.get(9));
        assertEquals("test11", cache.get(11));
    }
}
