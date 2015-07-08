package org.tenidwa.collections.utils;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public final class IterableToStreamTest {
    @Test
    public void iterableToStream() {
        Assert.assertEquals(
            Arrays.asList(1, 2, 3, 4, 5, 6),
            IterableToStream.stream(
                ImmutableList.of(1, 2, 3, 4, 5, 6)
            ).collect(java.util.stream.Collectors.toList())
        );
    }

    @Test
    public void iteratorToStream() {
        Assert.assertEquals(
            Arrays.asList(1, 2, 3, 4, 5, 6),
            IterableToStream.stream(
                ImmutableList.of(1, 2, 3, 4, 5, 6).iterator()
            ).collect(java.util.stream.Collectors.toList())
        );
    }

}
