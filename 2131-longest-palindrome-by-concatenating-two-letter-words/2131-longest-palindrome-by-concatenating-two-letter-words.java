class Solution {
    public int longestPalindrome(String[] words) {
        return mySol(words);
    }

    public int mySol(String[] words) {
        int ans = 0;
        Map<String, Integer> map = new HashMap();

        for (String word : words) {
            String palindrome = new StringBuilder(word).reverse().toString();

            if (map.containsKey(palindrome)) {
                map.put(palindrome, map.get(palindrome) - 1);

                ans += 4;

                if (map.get(palindrome) == 0) {
                    map.remove(palindrome);
                }
            } else {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        for (String word : map.keySet()) {
            if (word.charAt(0) == word.charAt(1)) {
                ans += 2;
                break;
            }
        }

        return ans;
    }
}