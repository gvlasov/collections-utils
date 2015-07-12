package org.tenidwa.collections.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.LinkedHashSet;
import org.junit.Assert;
import org.junit.Test;

public final class CollectorsTest {
    @Test
    public void toImmutableSet() {
        Assert.assertEquals(
            ImmutableSet.of(1, 2, 3),
            Arrays.asList(1, 2, 3).stream()
                .collect(Collectors.toImmutableSet())
        );
    }

    @Test
    public void toImmutableList() {
        Assert.assertEquals(
            ImmutableList.of(1, 2, 3),
            Arrays.asList(1, 2, 3).stream()
                .collect(Collectors.toImmutableList())
        );
    }

    @Test
    public void toLinkedHashSet() {
        final LinkedHashSet<Integer> expected = new LinkedHashSet<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        Assert.assertEquals(
            expected,
            Arrays.asList(1, 2, 3).stream()
                .collect(Collectors.toLinkedHashSet())
        );
    }
}
