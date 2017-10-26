import java.util.*;

public class HashTableChain<K, V> implements Map{
    //Data fields
    private LinkedList<Entry<K, V>>[] table;
    private int numKeys;
    private static final int CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 3.0;

    public HashTableChain() {
        table = new LinkedList[CAPACITY];
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if(table[index] == null) {
            return null; //key was never in table
        }

        for(Entry<K, V> nextItem : table[index]) {
            if(nextItem.key.equals(key)) {
                return nextItem.value;
            }
        }
        return null;
    }

    @Override
    public V put(Object key, Object value) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            //Create a new linked list at table[index]
            table[index] = new LinkedList<Entry<K, V>>();
        }

        //Search the list at table[index] to find the key
        for(Entry<K, V> nextItem : table[index]) {
            if(nextItem.key.equals(key)) {
                //Replace value for this key
                V oldValue = nextItem.value;
                nextItem.setValue((V) value);
                return oldValue;
            }
        }

        //assert: key is not in the table, add new item
        table[index].addFirst(new Entry<K, V>((K)key, (V)value));
        numKeys++;
        if(numKeys > (LOAD_THRESHOLD * table.length)) {
            rehash();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    @Override
    public int size() {
        return numKeys;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return false;
        }
        for(Entry<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if(table[index] == null) {
            return null; //key is not in table
        }

        for(Entry<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                V deletedValue = nextItem.value;
                //remove entry
                table[index].remove(nextItem);
                numKeys--;
                if (table[index].isEmpty()) {
                    table[index] = null;
                }
                return deletedValue;
            }
        }
        return null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (isEmpty() || size() <= 0) {
            return false;
        }

        for(LinkedList<Entry<K, V>> nextLL : table) {
            if (nextLL != null) {
                for(Entry<K, V> nextItem : nextLL) {
                    if (nextItem.value.equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        int index = 0;
        for(LinkedList<Entry<K, V>> nextLL : table) {
            if (nextLL != null) {
                table[index] = null;
                numKeys--;
            }
            index++;
        }
    }

    @Override
    public void putAll(Map m) {
        //This works because I know that Map m generics should be <? extends K, ? extends V>
        Set<K> mKeySet = m.keySet();
        for (K keyValue : mKeySet) {
            put(keyValue, m.get(keyValue));
        }
    }

    @Override
    public Set keySet() {
        Set<K> keySet = new HashSet<>();
        for(LinkedList<Entry<K, V>> nextLL : table) {
            if (nextLL != null) {
                for(Entry<K, V> nextItem : nextLL) {
                    keySet.add(nextItem.key);
                }
            }
        }
        return keySet;
    }

    @Override
    public Collection values() {
        Collection<V> values = new HashSet<>();
        for(LinkedList<Entry<K, V>> nextLL : table) {
            if (nextLL != null) {
                for(Entry<K, V> nextItem : nextLL) {
                    values.add(nextItem.value);
                }
            }
        }
        return values;
    }


    private void rehash() {
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[2 * oldTable.length + 1];

        numKeys = 0;

        for (LinkedList<Entry<K, V>> nextLL : oldTable) {
            if (nextLL != null) {
                for (Entry<K, V> entry : nextLL) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    @Override
    public Set<Map.Entry> entrySet() {
        Set<Map.Entry> stubSet = new HashSet<>();
        return stubSet;
        /*
        int index = 0;
        Set<Map.Entry> entrySet = new HashSet<>();
        for(Entry<K, V> nextItem : table[index]) {
            entrySet.add(nextItem);
        }
        /*
        for(LinkedList<Entry<K, V>> nextLL : table) {
            if (nextLL != null) {
                for(Entry<K, V> nextItem : table[index]) {
                    entrySet.add(nextItem);
                }
            }
            index++;
        }
        return entrySet;
        */
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            return "Empty";
        } else {
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    sb.append("Spot " + i + " " + table[i] + "\n");
                } else {
                    sb.append("Array location " + i + " is empty" + "\n");
                }
            }
        }
        return sb.toString();
    }

    private class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {
        @Override
        public int size() {
            return numKeys;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new SetIterator();
        }
    }

    private class SetIterator implements Iterator {
        private Entry<K, V> lastItemReturned;
        private Entry<K, V> nextItem;
        private int index = 0;

        public SetIterator() {

        }


        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Map.Entry<K, V> next() {
            return nextItem;
        }

        @Override
        public void remove() {
        }
    }

    private class Entry<K, V> implements Map.Entry<K, V>{
        private K key;
        private V value;


        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V val) {
            V oldValue = value;
            value = val;
            return oldValue;
        }

        @Override
        public String toString() {
            return "{"+key+"=>"+value+"}";
        }
    }
}
