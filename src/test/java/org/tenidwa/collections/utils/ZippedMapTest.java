package org.tenidwa.collections.utils;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ZippedMap}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $tendiwa-version$
 * @since 0.3
 */
public final class ZippedMapTest {
    @Test
    public void constructsFromEquallySizedLists() throws Exception {
        final ZippedMap<String, Integer> map = new ZippedMap<>(
            ImmutableList.of("Hello", "goodbye", "hey"),
            ImmutableList.of(7, 9, 15)
        );
        Assert.assertTrue(map.containsKey("Hello"));
        Assert.assertTrue(map.containsKey("goodbye"));
        Assert.assertTrue(map.containsKey("hey"));
        Assert.assertTrue(map.containsValue(7));
        Assert.assertTrue(map.containsValue(9));
        Assert.assertTrue(map.containsValue(15));
    }

    @Test(expected = Exception.class)
    public void failsToConstuctFromDifferentlySizedLists() throws Exception {
        new ZippedMap<>(
            ImmutableList.of("Ok", "Fine", "Nice"),
            ImmutableList.of(1, 2)
        );
    }
}
