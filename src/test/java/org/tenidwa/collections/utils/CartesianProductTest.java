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
            ImmutableSet.of(1, 2, 3);
        final ImmutableSet<Integer> right =
            ImmutableSet.of(1, 10, 100, 1000, 10000, 100000);
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
     * {@link CartesianProduct} can throw an exception if some of the results
     * of mapping the pairs with the function are not unique.
     */
    @Test(expected = IllegalStateException.class)
    public void disallowsDuplicatedMappingResults() {
        new CartesianProduct<>(
            ImmutableSet.of(1, 2),
            ImmutableSet.of(2, 1),
            CartesianProductTest::sum
        ).size();
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
