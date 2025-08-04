class Solution {
    public int totalFruit(int[] fruits) {
        return others_better(fruits);
    }

    public int others_better(int[] fruits) {
        int ans = 0;

        int leftFruit = -1;
        int rightFruit = -1;
        int count = 0;
        int rightFruitCount = 0;
        
        for (int f : fruits) {
            if (f == leftFruit || f == rightFruit) {
                count++;
            } else {
                count = rightFruitCount + 1;
            }

            if (f == rightFruit) {
                rightFruitCount++;
            } else {
                leftFruit = rightFruit;
                rightFruit = f;
                rightFruitCount = 1;
            }

            ans = Math.max(ans, count);
        }

        return ans;
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

            // int localSum = baskets.values().stream().mapToInt(Integer::intValue).sum();
            int localSum = right - left + 1;

            ans = Math.max(ans, localSum);
        }

        return ans;
    }
}