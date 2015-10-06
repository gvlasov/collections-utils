package org.tenidwa.collections.utils;

import com.google.common.collect.ImmutableSet;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit tests for {@link CartesianProduct}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.
 */
public final class CartesianProductTest {
    /**
     * {@link CartesianProduct} can have only size(left) * size(right) elements.
     */
    @Test
    public void hasATimesBElements() {
        final ImmutableSet<Integer> left =
            ImmutableSet.of(3, 4, 9);
        final ImmutableSet<Integer> right =
            ImmutableSet.of(1, 2, 9, 0, 3, 7, 11, 22);
        MatcherAssert.assertThat(
            new CartesianProduct<>(
                left,
                right,
                CartesianProductTest::sum
            ),
            Matchers.hasSize(left.size() * right.size())
        );
    }

    /**
     * Computes sum of two integers.
     * @param left One integer.
     * @param right Another integer.
     * @return Sum of two integers.
     */
    private static int sum(final int left, final int right) {
        return left + right;
    }

    /**
     * {@link CartesianProduct} can be a product of two empty sets.
     */
    @Test
    public void toleratesZeroElements() {
        MatcherAssert.assertThat(
            new CartesianProduct<>(
                ImmutableSet.of(),
                ImmutableSet.of(),
                (a, b) -> 3
            ),
            Matchers.hasSize(0)
        );
    }
}
