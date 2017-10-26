public class HashTableChainTester {
    public static void main(String[] args) {
        final int SIZE = 10;
        HashTableChain<Integer, Integer> hashTableChain = new HashTableChain<>();

        for (int i = 0; i < (SIZE*2); i ++) {
            Integer nextInt = (int) (32000 * Math.random());
            hashTableChain.put(nextInt, nextInt);
        }

        //System.out.println(hashTableChain.keySet());
        //System.out.println(hashTableChain.values());


        System.out.println(hashTableChain);

    }

}
