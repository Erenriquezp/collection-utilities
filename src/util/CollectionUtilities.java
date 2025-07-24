package util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class CollectionUtilities {

    // Creation
    public static <T>List<T> list() {
        return List.of();
    }

    public static <T> List<T> list(T t) {
        return List.of(t);
    }

    public static <T> List<T> list(List<T> ts) {
        return List.copyOf(ts);
    }

    @SafeVarargs
    public static <T> List<T> list(T... ts) {
        return List.of(ts.clone()); // protección inmutable
    }

    public static <T> List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }

    // HEAD and TAIL Safety
    public static <T> Optional<T> head(List<T> list) {
        return list.isEmpty() ? Optional.empty() : Optional.of(list.getFirst());
    }

    public static <T> Optional<List<T>> tail(List<T> list) {
        return list.isEmpty() ? Optional.empty() : Optional.of(list.subList(1, list.size()));
    }

    // FOLD
    public static <T, U> U foldLeft(List<T> list, U init, BiFunction<U, T, U> fn) {
        U acc = init;
        for (T t : list) {
            acc = fn.apply(acc, t);
        }
        return acc;
    }

    public static <T, U> U foldRight(List<T> list, U init, BiFunction<T, U, U> fn) {
        ListIterator<T> it = list.listIterator(list.size());
        U acc = init;
        while (it.hasNext()) {
            acc = fn.apply(it.previous(), acc);
        }
        return acc;
    }

    public static <T, U> U foldRightRecursive(List<T> list, U init, BiFunction<T, U, U> fn) {
        if (list.isEmpty()) return init;
        return fn.apply(list.get(0), foldRightRecursive(list.subList(1, list.size()), init, fn));
    }

    // REDUCE
    public static <T> Optional<T> reduceLeft(List<T> list, BinaryOperator<T> fn) {
        return list.isEmpty()
                ? Optional.empty()
                : Optional.of(foldLeft(list.subList(1, list.size()), list.getFirst(), fn));
    }

    public static <T> Optional<T> reduceRight(List<T> list, BinaryOperator<T> fn) {
        return list.isEmpty()
                ? Optional.empty()
                : Optional.of(foldRight(list.subList(0, list.size() - 1), list.getLast(), fn));
    }

    // APPEND and PREPEND
    public static <T> List<T> append(List<T> list, T t) {
        List<T> result = new ArrayList<>(list);
        result.add(t);
        return List.copyOf(result);
    }

    public static <T> List<T> prepend(List<T> list, T t) {
        List<T> result = new ArrayList<>(list);
        result.add(t);
        result.addAll(list);
        return List.copyOf(result);
    }

    // INVERTIR FUNCIONAL
    public static <T> List<T> invertir(List<T> list) {
        return foldLeft(list, new ArrayList<>(), (acc, elem) -> {
            acc.addFirst(elem);
            return acc;
        });
    }

    // MAP, FILTER, and FLATMAP
    public static <T, R> List<R> map(List<T> list, Function<T, R> fn) {
        return foldLeft(list, new ArrayList<>(), (acc, t) -> {
            acc.add(fn.apply(t));
            return acc;
        });
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> pred) {
        return foldLeft(list, new ArrayList<>(), (acc, t) -> {
            if (pred.test(t)) acc.add(t);
            return acc;
        });
    }

    public static <T, R> List<R> flatMap(List<T> list, Function<T, List<R>> fn) {
        return foldLeft(list, new ArrayList<>(), (acc, t) -> {
            acc.addAll(fn.apply(t));
            return acc;
        });
    }

    // ZIP  Combinar Clave - Valor
    public static <A, B> List<Pair<A, B>> zip(List<A> a, List<B> b) {
        int size = Math.min(a.size(), b.size());
        return IntStream.range(0, size)
                .mapToObj(i -> new Pair<>(a.get(i), b.get(i)))
                .toList();
    }

    // forEach FUNCIONAL
    public static <T> Ejecutable forEach(List<T> list, Effect<T> effect) {
        return foldLeft(list, () -> {}, (exec, elem) -> () -> {
            effect.apply(elem);
            exec.exec();
        });
    }

    // RANGE
    public static List<Integer> range(int start, int end) {
        return start >= end
                ? list()
                : prepend(range(start + 1, end), start);
    }

    public record Pair<A, B>(A a, B b) { }
}
