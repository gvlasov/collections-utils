package org.tenidwa.collections.utils;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A hacky solution to the issue of Java not being able to handle checked
 * exceptions in lambda expressions.
 * @see <a href="http://stackoverflow.com/a/27644392/1542343>StackOverflow
 *  question and answer</a>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $tendiwa-version$
 * @since 0.2
 */
public final class Rethrowing {
    public interface RethrowingFunction<T, R> {
        R apply(T x) throws Exception;
    }

    public interface RethrowingBiFunction<T1, T2, R> {
        R apply(T1 a, T2 b) throws Exception;
    }

    public static <T, R> Function<T, R> rethrowFunction(RethrowingFunction<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception exception) {
                throwAsUnchecked(exception);
                return null;
            }
        };
    }

    public static <T1, T2, R> BiFunction<T1, T2, R> rethrowBiFunction(RethrowingBiFunction<T1, T2, R> function) {
        return (a, b) -> {
            try {
                return function.apply(a, b);
            } catch (Exception exception) {
                throwAsUnchecked(exception);
                return null;
            }
        };
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E {
        throw (E) exception;
    }
}
