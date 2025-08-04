class Solution {
    public int totalFruit(int[] fruits) {
        return mySol(fruits);
    }

    public int mySol(int[] fruits) {
        Map<Integer, Integer> baskets = new HashMap();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < fruits.length; right++) {
            int f = fruits[right];

            while (baskets.size() == 2 && !baskets.containsKey(f)) {
                int remove = fruits[left++];

                baskets.put(remove, baskets.get(remove) - 1);

                if (baskets.get(remove) == 0) {
                    baskets.remove(remove);
                }
            }

            baskets.put(f, baskets.getOrDefault(f, 0) + 1);

            int localSum = baskets.values().stream().mapToInt(Integer::intValue).sum();

            ans = Math.max(ans, localSum);
        }

        return ans;
    }
}