package org.tenidwa.collections.utils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit tests for {@link AugmentedMap}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.7.0
 */
public final class AugmentedMapTest {
    @Test
    public void augmentsBaseMap() {
        final AugmentedMap<String, Integer> augmented =
            new AugmentedMap<>(
                ImmutableMap.of("Jeff", 4),
                ImmutableSet.of("Rodney", "Bill", "Kevin"),
                String::length
            );
        MatcherAssert.assertThat(augmented, Matchers.hasKey("Rodney"));
        MatcherAssert.assertThat(augmented, Matchers.hasKey("Bill"));
        MatcherAssert.assertThat(augmented, Matchers.hasKey("Kevin"));
        MatcherAssert.assertThat(augmented, Matchers.hasKey("Jeff"));
        MatcherAssert.assertThat(
            augmented.get("Rodney"),
            Matchers.equalTo("Rodney".length())
        );
    }

    @Test
    public void augmentsEmptyBaseMap() {
        final AugmentedMap<String, Integer> augmented =
            new AugmentedMap<>(
                ImmutableMap.of(),
                ImmutableSet.of("Rodney", "Bill", "Kevin"),
                String::length
            );
        MatcherAssert.assertThat(augmented, Matchers.hasKey("Rodney"));
        MatcherAssert.assertThat(augmented, Matchers.hasKey("Bill"));
        MatcherAssert.assertThat(augmented, Matchers.hasKey("Kevin"));
        MatcherAssert.assertThat(
            augmented.get("Rodney"),
            Matchers.equalTo("Rodney".length())
        );
    }

    @Test
    public void allowsOverlappingKeySets() {
        final AugmentedMap<String, Integer> augmented =
            new AugmentedMap<>(
                ImmutableMap.of(
                    "Anna", 4,
                    "Emma", 4
                ),
                ImmutableSet.of("Anna", "Susanna"),
                String::length
            );
        MatcherAssert.assertThat(
            augmented.keySet(),
            Matchers.hasSize(3)
        );
        MatcherAssert.assertThat(
            augmented.get("Susanna"),
            Matchers.equalTo("Susanna".length())
        );
    }
}
