package org.tenidwa.collections.utils;

import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.stream.*;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * UnitTests for {@link Rethrowing}.
 * @author Gerogy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2
 */
public final class RethrowingTest {
    @Test(expected = IOException.class)
    public void allowsUsingCheckedExceptions() {
        Stream.of(1,2,3)
            .map(Rethrowing.rethrowFunction(Reader::new))
            .collect(Collectors.toList());
    }

    @Test(expected = IOException.class)
    public void allowsUsingCheckedExceptionsInBiFunctions() {
        new TransitiveMap<>(
            ImmutableMap.of(
                "dude", 4,
                "man", 3
            ),
            Rethrowing.rethrowBiFunction(
                (dude, man) -> new Reader(man)
            )
        );
    }

    /**
     * Just throws IOException upon construction.
     */
    private static class Reader {
        public Reader(int i) throws IOException{
            throw new IOException();
        }
    }
}
