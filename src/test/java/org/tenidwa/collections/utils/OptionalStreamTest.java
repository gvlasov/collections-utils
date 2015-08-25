package org.tenidwa.collections.utils;

import java.util.Optional;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Unit tests for {@link OptionalStream}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class OptionalStreamTest {
    /**
     * {@link OptionalStream} can create a Stream of 1 element from a
     * non-empty Optional.
     * @throws Exception If fails
     */
    @Test
    public void createsNonEmptyStreamFromNonEmptyOptional() throws Exception {
        MatcherAssert.assertThat(
            new OptionalStream<>(Optional.of(999)).count(),
            CoreMatchers.equalTo(1L)
        );
    }

    /**
     * {@link OptionalStream} can create an empty Stream from an empty Optional.
     * @throws Exception
     */
    @Test
    public void createsEmptyStreamFromEmptyOptional() throws Exception {
        MatcherAssert.assertThat(
            new OptionalStream<>(Optional.empty()).count(),
            CoreMatchers.equalTo(0L)
        );
    }
}
