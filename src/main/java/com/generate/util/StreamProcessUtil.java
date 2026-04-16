package com.generate.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamProcessUtil {

    /// 아키텍쳐 구조를 변경하지 않고 간편하게 이중 맵의 키를 교환하는 메서드
    public static <K1, K2, V> Map<K2, Map<K1, V>> transpose(Map<K1, Map<K2, V>> originalMap) {
        Map<K2, Map<K1, V>> resultMap = new HashMap<>();

        originalMap.forEach((k1, innerMap) ->
                innerMap.forEach((k2, value) ->
                        resultMap.computeIfAbsent(k2, k -> new HashMap<>()).put(k1, value)
                )
        );
        return resultMap;
    }

    /// NPE 방어용 필터링 공용 메서드
    private static <T> Stream<T> toNonNullStream(Stream<T> stream) {
        return stream.filter(Objects::nonNull);
    }

    private static<T> Stream<T> toNonNullStream(Collection<T> collection) {
        return toNonNullStream(collection.stream());
    }

    /// 맵 인덱스를 변경하지 않고 안의 값만 바꾸는 메서드
    public static <K, V, R> Map<K, R> transformValues(
            Map<K, V> sourceMap,
            Function<V, R> valueMapper
    ) {
        return toNonNullStream(sourceMap.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> valueMapper.apply(entry.getValue()),
                        (existing, replacement) -> replacement
                ));
    }

    public static <K, T> Map<K, T> toMap
            (List<T> list, Function<T, K> keymap) {
        return toMap(list, keymap, Function.identity());
    }

    /// 단순 리스트를 key 기준으로 맵을 만드는 메서드
    public static <K, T, R> Map<K, R> toMap
            (List<T> list, Function<T, K> keymap, Function<T, R> valueMapper) {
        return toNonNullStream(list)
                .collect(Collectors.toMap(
                        keymap,
                        valueMapper,
                        (existing, replacement) -> replacement
                ));
    }

    // List<Generic> list 를 Map<Key, List<Result>> 로
    // 데이터 형 변환과 맵으로 만드는 메서드
    public static <K, T, R> Map<K, List<R>> toListMap
    (List<T> list, Function<T, K> keymap, Function<T, R> valueMapper) {
        return toNonNullStream(list)
                .collect(
                        Collectors.groupingBy(
                                keymap,
                                Collectors.mapping
                                        (valueMapper, Collectors.toList())
                        )
                );
    }

    public static <K1, K2, T> Map<K1, Map<K2, T>> toNestedMap(
            List<T> list,
            Function<T, K1> keymap1,
            Function<T, K2> keymap2) {
        return toNestedMap(list, keymap1, keymap2, Function.identity());
    }

    /// 내용이 단일 객체인 이중 맵으로 만드는 메서드
    public static <K1, K2, T, R> Map<K1, Map<K2, R>> toNestedMap(
            List<T> list,
            Function<T, K1> keymap1,
            Function<T, K2> keymap2,
            Function<T, R> valueMapper) {
        return toNonNullStream(list)
                .collect(
                        Collectors.groupingBy(
                                keymap1,
                                Collectors.toMap(
                                        keymap2,
                                        valueMapper,
                                        (existing, replacement) -> replacement
                                )
                        )
                );
    }

    /// 내용이 객체 리스트인 이중 맵으로 만드는 메서드
    public static <K1, K2, T, R> Map<K1, Map<K2, List<R>>> toNestedListMap
            (List<T> list,
             Function<T, K1> key1Mapper,
             Function<T, K2> key2Mapper,
             Function<T, R> valueMapper) {
        return toNonNullStream(list)
                .collect(
                        Collectors.groupingBy(
                                key1Mapper,
                                LinkedHashMap::new,
                                Collectors.groupingBy(
                                        key2Mapper,
                                        LinkedHashMap::new,
                                        Collectors.mapping(
                                                valueMapper,
                                                Collectors.toList()
                                        )
                                )
                        )
                );
    }

    public static <K1, K2, T> Map<K1, Map<K2, List<T>>> toNestedListMap
            (List<T> list,
             Function<T, K1> key1Mapper,
             Function<T, K2> key2Mapper) {
        return toNestedListMap(list, key1Mapper, key2Mapper, Function.identity());
    }

    /// 복합 컬렉션 객체를 객체 리스트로 평탄화 하는 메서드
    public static <T> List<T> flatten(Map<?, ? extends Collection<T>> map) {
        return toNonNullStream(map.values())
                .flatMap(Collection::stream)
                .toList();
    }

    public static <T, R> List<R> flatten(List<T> list, Function<T, Collection<R>> flatMapper ) {
        return toNonNullStream(list)
                .map(flatMapper)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /// 특정 형태의 리스트를 다른 데이터형의 리스트로 변환하는 메서드
    public static <T, R> List<R> mapToList(
            Collection<T> collection, Function<T, R> mapper) {
        return toNonNullStream(collection).map(mapper).toList();
    }
}
