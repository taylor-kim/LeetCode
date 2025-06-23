class Solution {
    public int minimumDeletions(String word, int k) {
        return mySol(word, k);
    }

    public int mySol(String word, int k) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c -  'a']++;
        }

        TreeMap<Integer, Integer> map = new TreeMap();

        for (int count : freq) {
            if (count == 0) continue;
            map.put(count, map.getOrDefault(count, 0) + 1);
        }

        int right = map.lastKey();
        int left = map.firstKey();

        int ans = 0;

        while (right - left > k) {
            int removeRight = (right - left - k) * map.get(right);
            int removeLeft = left * map.get(left);

            if (removeLeft < removeRight) {
                ans += removeLeft;
                left = map.higherKey(left);
            } else {
                ans += removeRight;
                right = map.lowerKey(right);
            }
        }

        return ans;
    }
}