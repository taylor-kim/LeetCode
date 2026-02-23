class Solution {
    public boolean hasAllCodes(String s, int k) {
        return mySol(s, k);
    }

    public boolean mySol(String s, int k) {
        int combination = (int)Math.pow(2, k);

        Set<String> set = new HashSet();

        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            if (right - left + 1 > k) {
                left++;
            }

            if (right - left + 1 == k) {
                set.add(s.substring(left, right + 1));
            }
        }

        return set.size() >= combination;
    }
}