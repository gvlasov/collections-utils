package org.tenidwa.collections.utils;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * A map that retains keys of the original map, but whose values are a
 * function of key/value pairs from the original map.
 * <p/>
 * Original map maps k to v.
 * <br/>
 * Transitive map maps k to f(k, v).
 * <pre>
 *     new TransitiveMap(
 *       ImmutableMap.of(
 *         "dude", 4,
 *         "man", 3
 *       ),
 *       (k, v) -> new Placard(k ,v)
 *     ); // { "dude" -> Placard("dude", 4), "man" -> Placard("man", 3) }
 * </pre>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.10.0
 */
public final class TransitiveMap<K, V2> extends ForwardingMap<K, V2> {
    /**
     * Precomputed delegate map.
     */
    private final transient Map<K, V2> map;

    public <V1> TransitiveMap(
        final Map<K, V1> base,
        final BiFunction<K, V1, V2> function
    ) {
        this.map = TransitiveMap.createMap(base, function);
    }

    private static <K, V1, V2> Map<K, V2> createMap(
        final Map<K, V1> base,
        final BiFunction<K, V1, V2> function
    ) {
        final ImmutableMap.Builder<K, V2> builder = ImmutableMap.builder();
        for (final Entry<K, V1> e : base.entrySet()) {
            builder.put(
                e.getKey(),
                function.apply(e.getKey(), e.getValue())
            );
        }
        return builder.build();
    }

    @Override
    protected Map<K, V2> delegate() {
        return this.map;
    }
}
