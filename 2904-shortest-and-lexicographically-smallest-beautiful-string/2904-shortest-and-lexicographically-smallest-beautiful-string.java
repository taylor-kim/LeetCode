class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        return mySol(s, k);
    }

    public String mySol(String s, int k) {
        String ans = "";

        int left = 0;
        int count = 0;

        for (int right = 0 ; right < s.length(); right++) {
            if (s.charAt(right) == '1') {
                count++;
            }

            while (count > k) {
                if (s.charAt(left++) == '1') {
                    count--;
                }
            }

            while (count == k && s.charAt(left) == '0') {
                left++;
            }

            if (count == k) {
                String candidate = s.substring(left, right + 1);

                if (ans.isEmpty() 
                    || (candidate.length() < ans.length()) 
                    || (candidate.length() == ans.length() && candidate.compareTo(ans) < 0)) {
                    ans = candidate;
                }
            }
        }

        return ans;
    }
}