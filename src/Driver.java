import java.util.Map;
import java.util.SortedMap;

public class Driver {
    public static void main(String[] args) {
        HashTableChain<String, String> hashTableChain = new HashTableChain<>();
        HashTableChain<Object, Object> testMap = new HashTableChain<>();
        testMap.put("A", "avocado");
        testMap.put("F", "fruit");
        testMap.put("E", "eating");
        System.out.println(testMap.keySet());
        System.out.println(testMap.values());
        System.out.println(testMap.entrySet());
        System.out.println(hashTableChain.isEmpty());
        System.out.println(hashTableChain.size());
        System.out.println(hashTableChain.containsValue("bear"));

        hashTableChain.put("A", "apple");
        hashTableChain.put("B", "bear");
        hashTableChain.put("C", "cucumber");
        System.out.println(hashTableChain.keySet());

        System.out.println(hashTableChain.isEmpty());
        System.out.println(hashTableChain.size());

        System.out.println(hashTableChain.containsKey("D"));
        System.out.println(hashTableChain.containsKey("A"));

        System.out.println(hashTableChain.get("A"));
        System.out.println(hashTableChain.get("B"));
        System.out.println(hashTableChain.get("C"));
        hashTableChain.put("A1", "apple");


        System.out.println(hashTableChain.containsKey("A"));

        System.out.println(hashTableChain.containsValue("bear"));

        //hashTableChain.clear();

        hashTableChain.putAll(testMap);


        System.out.println(hashTableChain.keySet());
        System.out.println(hashTableChain.values());

        //System.out.println(hashTableChain.entrySet());
        hashTableChain.put("A2", "apple");
        System.out.println(hashTableChain);






    }
}
