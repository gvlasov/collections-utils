package org.tenidwa.collections.utils;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit tests for {@link Measurement}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.7.0
 */
public final class MeasurementTest {
    @Test
    public void measuresObjects() {
        MatcherAssert.assertThat(
            new Measurement<>(String::length)
                .compare("looooooooong", "short"),
            Matchers.equalTo(1)
        );
        MatcherAssert.assertThat(
            new Measurement<>(String::length)
                .compare("short", "looooooong"),
            Matchers.equalTo(-1)
        );
        MatcherAssert.assertThat(
            new Measurement<>(String::length)
                .compare("two", "one"),
            Matchers.equalTo(0)
        );
    }
}
