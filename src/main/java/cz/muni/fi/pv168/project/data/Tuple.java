package cz.muni.fi.pv168.project.data;

public record Tuple<K, V, T>(K key, V value, T val) {
}
