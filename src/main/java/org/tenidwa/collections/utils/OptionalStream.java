package org.tenidwa.collections.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Transforms a {@link java.util.Optional} to a {@link java.util.stream.Stream}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class OptionalStream<T> implements Stream<T> {
    private final Optional<T> optional;
    private Stream<T> delegate;

    public OptionalStream(final Optional<T> opt) {
        this.optional = opt;
    }

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return this.delegate().filter(predicate);
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> function) {
        return this.delegate().map(function);
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> toIntFunction) {
        return this.delegate().mapToInt(toIntFunction);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> toLongFunction) {
        return this.delegate().mapToLong(toLongFunction);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        return this.delegate().mapToDouble(toDoubleFunction);
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> function) {
        return this.delegate().flatMap(function);
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> function) {
        return this.delegate().flatMapToInt(function);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> function) {
        return this.delegate().flatMapToLong(function);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> function) {
        return this.delegate().flatMapToDouble(function);
    }

    @Override
    public Stream<T> distinct() {
        return this.delegate().distinct();
    }

    @Override
    public Stream<T> sorted() {
        return this.delegate().sorted();
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return this.delegate().sorted(comparator);
    }

    @Override
    public Stream<T> peek(Consumer<? super T> consumer) {
        return this.delegate().peek(consumer);
    }

    @Override
    public Stream<T> limit(long l) {
        return this.delegate().limit(l);
    }

    @Override
    public Stream<T> skip(long l) {
        return this.delegate().skip(l);
    }

    @Override
    public void forEach(Consumer<? super T> consumer) {
        this.delegate.forEach(consumer);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> consumer) {
        this.delegate.forEachOrdered(consumer);
    }

    @Override
    public Object[] toArray() {
        return this.delegate().toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> intFunction) {
        return this.delegate().toArray(intFunction);
    }

    @Override
    public T reduce(T t, BinaryOperator<T> binaryOperator) {
        return this.delegate().reduce(t, binaryOperator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> binaryOperator) {
        return this.delegate().reduce(binaryOperator);
    }

    @Override
    public <U> U reduce(
        U u,
        BiFunction<U, ? super T, U> biFunction,
        BinaryOperator<U> binaryOperator
    ) {
        return this.delegate().reduce(u, biFunction, binaryOperator);
    }

    @Override
    public <R> R collect(
        Supplier<R> supplier,
        BiConsumer<R, ? super T> biConsumer,
        BiConsumer<R, R> biConsumer1
    ) {
        return this.delegate().collect(supplier, biConsumer, biConsumer1);
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return this.delegate().collect(collector);
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return this.delegate().min(comparator);
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return this.delegate().max(comparator);
    }

    @Override
    public long count() {
        return this.delegate().count();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return this.delegate().anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return this.delegate().allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return this.delegate().noneMatch(predicate);
    }

    @Override
    public Optional<T> findFirst() {
        return this.delegate().findFirst();
    }

    @Override
    public Optional<T> findAny() {
        return this.delegate().findAny();
    }

    @Override
    public Iterator<T> iterator() {
        return this.delegate().iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return this.delegate().spliterator();
    }

    @Override
    public boolean isParallel() {
        return this.delegate().isParallel();
    }

    @Override
    public Stream<T> sequential() {
        return this.delegate().sequential();
    }

    @Override
    public Stream<T> parallel() {
        return this.delegate().parallel();
    }

    @Override
    public Stream<T> unordered() {
        return this.delegate().unordered();
    }

    @Override
    public Stream<T> onClose(Runnable runnable) {
        return this.delegate().onClose(runnable);
    }

    @Override
    public void close() {
        this.delegate().close();
    }

    private Stream<T> delegate() {
        if (this.delegate == null) {
            if (this.optional.isPresent()) {
                this.delegate = Stream.of(this.optional.get());
            } else {
                this.delegate = Stream.empty();
            }
        }
        return this.delegate;
    }
}
