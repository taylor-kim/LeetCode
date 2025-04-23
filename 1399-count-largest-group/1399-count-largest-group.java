class Solution {
    public int countLargestGroup(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        Map<Integer, Integer> countOfGroups = new HashMap();
        int maxCount = 0;

        while (n > 0) {
            int num = n;
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            countOfGroups.put(sum, countOfGroups.getOrDefault(sum, 0) + 1);
            maxCount = Math.max(maxCount, countOfGroups.get(sum));
            n--;
        }

        int ans = 0;

        for (int count : countOfGroups.values()) {
            if (count == maxCount) ans++;
        }

        return ans;
    }
}