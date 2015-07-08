package org.tenidwa.collections.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

public final class StreamIterableTest {
    @Test
    public void containsRightElements() {
        final Iterator<Integer> iterator = new StreamIterable<>(
            Arrays.asList(1, 2, 3, 4).stream()
        )
            .iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(1, (int) iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(2, (int) iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(3, (int) iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(4, (int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void canWorkWithEmptyStream() {
        new StreamIterable<>(Stream.of())
            .iterator()
            .next();
    }

}
