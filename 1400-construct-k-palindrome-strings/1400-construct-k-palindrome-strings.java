class Solution {
    public boolean canConstruct(String s, int k) {
        return mySol3(s, k);
    }

    public boolean mySol3(String s, int k) {
        if (s.length() == k) return true;

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int oddCount = 0;
        int evenCount = 0;

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == 0) continue;
            
            if (freq[i] % 2 == 0) {
                evenCount += freq[i] / 2;
            } else {
                evenCount += freq[i] / 2;
                oddCount++;
            }
        }

        // System.out.println(Arrays.toString(freq));

        // System.out.println(String.format("evenCount:%d, oddCount:%d, k:%d", evenCount, oddCount, k));

        k -= oddCount;

        if (k < 0) return false;

        if (evenCount * 2 < k) return false;

        return true;
    }

    public boolean mySol2_fail(String s, int k) {
        return topdown(s, 0, k);
    }

    public boolean topdown(String s, int index, int k) {
        if (index >= s.length()) return k == 0;

        for (int right = index; right < s.length(); right++) {
            if (isPalindrome2(s, index, right)) {
                if (topdown(s, right + 1, k - 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isPalindrome2(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;

            left++;
            right--;
        }

        return true;
    }

    public boolean mySol_fail(String s, int k) {
        if (s.length() == k) return true;

        int matching = 0;

        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s, i, i)) {
                System.out.println(String.format("l:%d, r:%d", i, i));
                if (--matching == k) return true;
            }

            if (i + 1 < s.length() && isPalindrome(s, i, i + 1)) {
                System.out.println(String.format("l:%d, r:%d", i, i + 1));
                if (--matching == k) return true;
            }
        }

        System.out.println(matching);

        return false;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) return false;

            left--;
            right++;
        }

        return left < 0 || right >= s.length();
    }
}