package net.tuxed.vpnconfigimporter.utils;

import android.support.test.filters.LargeTest;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Test which tests the TTL cache.
 * Created by Daniel Zolnai on 2016-10-20.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class TTLCacheTest {
    @Test
    public void testPurge() {
        TTLCache<Object> cache = new TTLCache<>(0);
        cache.put("abc", new Object());
        cache.put("cde", new Object());
        cache.purge();
        assertEquals(0, cache.getEntries().size());
    }

    @Test
    public void testNoPurge() {
        TTLCache<Object> cache = new TTLCache<>(3);
        cache.put("abc", new Object());
        cache.put("cde", new Object());
        cache.purge();
        assertEquals(2, cache.getEntries().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testInvalidModify() {
        TTLCache<Object> cache = new TTLCache<>(10);
        cache.put("abc", new Object());
        cache.put("cde", new Object());
        // This should not be allowed.
        cache.getEntries().remove("abc");
    }

}