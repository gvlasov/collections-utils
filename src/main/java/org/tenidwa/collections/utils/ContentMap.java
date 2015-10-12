package org.tenidwa.collections.utils;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Map that uses function of K as keys instead of K.
 * <p/>
 * Its purpose is to allow mapping from keys that don't support
 * {@link Object#equals(Object)}. When using the usual {@link Map} that has an
 * interface as a key type, there is no sane way to treat instances of
 * different types as equal, because then we'd have to:
 * <ul>
 *     <li>re-implement {@link Object#equals(Object)} in every type (which would
 *     be a lot of duplicated code);</li>
 *     <li>or extend every implementation of the interface with an abstract
 *     class that implements {@link Object#equals(Object)} using methods of that
 *     interface (which would still be duplicating code, not per interface
 *     implementation but per interface).</li>
 * </ul>
 * <p/>
 * "Content" in "ContentMap" means "something equatable that is obtained with
 * public methods of interface {@code K}"
 * <p/>
 * For example, if {@code PersonWithoutEquals} and {@code PersonFromStream}
 * don't implement equals but have method {@code Person#name()} that returns
 * {@link String}, we could map {@code Person}s to something else like this:
 * <pre>
 *     Map<Person, Integer> map =
 *         new ContentMap<Person, String, Integer>(Person::name);
 *     map.put(new PersonWithoutEquals("Jeff"), 1);
 *     map.get(new PersonFromStream(streamWithNameJeff)); // 1
 * </pre>
 * <p/>
 * This map has deterministic ordering.
 * <p/>
 * This map doesn't support methods {@link Map#keySet()} and
 * {@link Map#entrySet()} because it doesn't store the keys of type
 * {@code K}, it just uses those to obtain the "hidden" keys of type {@code C}.
 * @param <K> Key type
 * @param <C> Some content obtainable from K
 * @param <V> Value type
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.6.0
 */
public final class ContentMap<K, C, V> implements Map<K, V> {
    /**
     * Map from content of the base map to its values.
     */
    private final transient Map<C, V> map;

    /**
     * Function to extract content from keys.
     */
    private final transient Function<K, C> function;

    /**
     * Ctor.
     * @param base Base map.
     * @param function Function to get content of a key.
     */
    public ContentMap(
        final Map<K, V> base,
        final Function<K, C> function
    ) {
        this.function = function;
        this.map = this.mapContentToValues(base);
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        return this.map.containsKey(this.key(o));
    }

    @Override
    public boolean containsValue(Object o) {
        return this.map.containsValue(o);
    }

    @Override
    public V get(Object o) {
        return this.map.get(this.key(o));
    }

    @Deprecated
    @Override
    public V put(K k, V v) {
        throw new UnsupportedOperationException(
            "Put operation is not supported"
        );
    }

    @Deprecated
    @Override
    public V remove(Object o) {
        throw new UnsupportedOperationException(
            "Remove operation is not supported"
        );
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException(
            "Put all operation is not supported"
        );
    }

    @Deprecated
    @Override
    public void clear() {
        throw new UnsupportedOperationException(
            "Clear operation is not supported"
        );
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException(
            "keySet() operation is not supported by ContentMap"
        );
    }

    @Override
    public Collection<V> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException(
            "entrySet() operation is not supported by ContentMap"
        );
    }

    @SuppressWarnings("unchecked")
    private C key(Object o) {
        return this.function.apply((K)o);
    }

    /**
     * Maps the content of the base map to its values.
     * @param base Base map
     * @return Map from content of the base map to its values.
     */
    private Map<C, V> mapContentToValues(final Map<K, V> base) {
        final ImmutableMap.Builder<C, V> builder = ImmutableMap.builder();
        for (final Entry<K, V> entry : base.entrySet()) {
            builder.put(
                this.key(entry.getKey()),
                entry.getValue()
            );
        }
        return builder.build();
    }
}
