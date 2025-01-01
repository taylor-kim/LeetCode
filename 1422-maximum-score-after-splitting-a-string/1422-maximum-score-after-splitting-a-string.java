class Solution {
    public int maxScore(String s) {
        return official_onepass(s);
    }
    
    public int official_onepass(String s) {
        int leftZeros = 0;
        int leftOnes = 0;

        /**
            score = leftZeros + rightOnes
            totalOnes = leftOnes + rightOnes
            rightOnes = totalOnes - leftOnes
            score = leftZeros + totalOnes - leftOnes
            totalOnes is a constant. so, find the max(leftZeros - leftOnes) to maximize score.
         */

        int ans = Integer.MIN_VALUE;
        
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                leftZeros++;
            } else {
                leftOnes++;
            }

            ans = Math.max(ans, leftZeros - leftOnes);
        }

        if (s.charAt(s.length() - 1) == '1') leftOnes++;

        return ans + leftOnes;
    }

    public int constant_space(String s) {
        int ones = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        int zeros = 0;
        int ans = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                zeros++;
            } else {
                ones--;
            }
            ans = Math.max(ans, zeros + ones);
        }

        return ans;
    }

    public int mySol_spaceopt(String s) {
        int n = s.length();
        int[] zeros = new int[n];

        for (int i = 0; i < n; i++) {
            zeros[i] = s.charAt(i) == '0' ? 1 : 0;

            if (i > 0) {
                zeros[i] += zeros[i - 1];
            }
        }

        int ans = 0;
        int ones = 0;

        for (int i = n - 1; i >= 1; i--) {
            if (s.charAt(i) == '1') ones++;

            ans = Math.max(ans, zeros[i - 1] + ones);
        }

        return ans;
    }

    public int mySol(String s) {
        int n = s.length();
        int[] zeros = new int[n];
        int[] ones = new int[n];

        for (int i = 0; i < n; i++) {
            zeros[i] = s.charAt(i) == '0' ? 1 : 0;

            ones[n - i - 1] = s.charAt(n - i - 1) == '1' ? 1 : 0;

            if (i > 0) {
                zeros[i] += zeros[i - 1];
            }

            if (n - i < n) {
                ones[n - i - 1] += ones[n - i];
            }
        }

        int ans = 0;

        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, zeros[i - 1] + ones[i]);
        }

        return ans;
    }
}