public class HashTableOpen<K, V> implements KWHashMap<K, V> {
    //Data fields
    private Entry<K, V>[] table;
    private static final int START_CAPACITY = 101;
    private double LOAD_THRESHOLD = 0.75;
    private int numKeys;
    private int numDeletes;
    private final Entry<K, V> DELETED = new Entry<K, V>(null, null);

    public HashTableOpen() {
        table = new Entry[START_CAPACITY];
    }

    private class Entry<K, V> {
        private K key;
        private V value;


        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V val) {
            value = val;
            return value;
        }
    }



    private int find(Object key) {
        int index = 0;
        index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        while (table[index] != null && table[index] != key) {
            index++;
            if (index >= table.length) {
                index = 0;
            }
        }
        return index;
    }

    private void rehash() {

    }


    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    public int size() {
        return 0;
    }


}
