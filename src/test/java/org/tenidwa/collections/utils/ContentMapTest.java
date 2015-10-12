package org.tenidwa.collections.utils;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit tests for {@link ContentMap}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.6.0
 */
public final class ContentMapTest {
    @Test
    public void detectsIdenticalValues() {
        final ContentMap<Dude, String, Integer> map = new ContentMap<>(
            Dude::name
        );
        map.put(new CombinedDude("je", "ff"), 2);
        MatcherAssert.assertThat(
            map.containsKey(new PlainDude("jeff")),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            map.get(new PlainDude("jeff")),
            Matchers.equalTo(2)
        );
    }

    interface Dude {
        String name();
    }

    final static class PlainDude implements Dude {
        private final String name;

        PlainDude(final String name) {
            this.name = name;
        }

        @Override
        public String name() {
            return this.name;
        }
    }

    final static class CombinedDude implements Dude {
        private final String one;
        private final String two;

        @Override
        public String name() {
            return this.one + this.two;
        }

        CombinedDude(final String one, final String two) {
            this.one = one;
            this.two = two;
        }
    }
}
