package org.tenidwa.collections.utils;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * <a href="http://en.wikipedia.org/wiki/Cartesian_product">Cartesian
 * product</a> of two sets.
 * <p/>
 * Computes all possible pairs where the first element is from set A, and the
 * second element is from set B, and maps those pairs to whatever you need.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.5.0
 */
public final class CartesianProduct<A, B, R> extends ForwardingList<R> {
    /**
     * First multiplier.
     */
    private final transient Set<A> one;

    /**
     * Second multiplier.
     */
    private final transient Set<B> two;

    /**
     * Function to produce the results from pairs.
     */
    private final transient BiFunction<A, B, R> function;

    /**
     * Saved result.
     */
    private transient List<R> result;

    /**
     * Ctor.
     * @param one First multiplier
     * @param two Second multiplier
     */
    public CartesianProduct(
        final Set<A> one,
        final Set<B> two,
        final BiFunction<A, B, R> function
    ) {
        this.one = one;
        this.two = two;
        this.function = function;
    }

    @Override
    protected List<R> delegate() {
        if (this.result == null) {
            this.result = this.multiplication();
        }
        return this.result;
    }

    /**
     * Creates Cartesian product of two lists.
     */
    private List<R> multiplication() {
        final ImmutableList.Builder<R> builder = ImmutableList.builder();
        for (final A left : this.one) {
            for (final B right : this.two) {
                builder.add(this.function.apply(left, right));
            }
        }
        return builder.build();
    }
}
