package org.tenidwa.collections.utils;

import com.google.common.collect.ImmutableMap;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit tests for {@link TransitiveMap}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.10.0
 */
public final class TransitiveMapTest {
    @Test
    public void usesFunctionToCreateNewValues() {
        MatcherAssert.assertThat(
            new TransitiveMap<String, String>(
                ImmutableMap.of(
                    "dude", 4,
                    "man", 3
                ),
                (name, length) -> length + "-letter name " + name
            ),
            Matchers.allOf(
                Matchers.hasEntry("dude", "4-letter name dude"),
                Matchers.hasEntry("man", "3-letter name man")
            )
        );
    }
}
