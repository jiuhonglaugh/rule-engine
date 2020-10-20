package aviator.entity;

import java.util.HashMap;

public class MapJ<K, V> {
    private K key;
    private V value;

    public K getKey() {
        return this.key;
    }

    public void setKey(final K key) {
        this.key = key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(final V value) {
        this.value = value;
    }

    public MapJ(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.key + ":" + this.value;
    }

    public static void main(String[] args) {
        MapJ<Integer, Integer> integerIntegerMapJ = new MapJ<>(1, 1);
        System.out.println(integerIntegerMapJ.toString());
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        String put = stringStringHashMap.put("1", "1");
        System.out.println(stringStringHashMap.toString());
    }
}
