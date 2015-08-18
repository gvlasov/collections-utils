package org.tenidwa.collections.utils;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $tendiwa-version$
 * @since 0.3
 */
public final class ZippedMap<K, V> implements Map<K, V> {
    private final Map<K, V> delegate;

    public ZippedMap(final List<K> keys, final List<V> values)
        throws Exception {
        if (keys.size() != values.size()) {
            throw new Exception(
                String.format(
                    "Keys size (%s) and values size (%s) must be the same",
                    keys.size(),
                    values.size()
                )
            );
        }
        this.delegate = ZippedMap.createMap(keys, values);
    }

    private static <K, V> Map<K, V> createMap(
        final List<K> keys,
        final List<V> values
    ) {
        final int size = keys.size();
        final ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        for (int i=0; i< size; i++) {
            builder.put(keys.get(i), values.get(i));
        }
        return builder.build();
    }

    @Override
    public int size() {
        return this.delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        return this.delegate.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return this.delegate.containsValue(o);
    }

    @Override
    public V get(Object o) {
        return this.delegate.get(o);
    }

    @Override
    public V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        return this.delegate.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.delegate.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.delegate.entrySet();
    }
}
