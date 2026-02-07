class Solution {
    public int minimumDeletions(String s) {
        return try_20260207_2(s);
    }
    
    public int try_20260207_2(String s) {
        int n = s.length();
        int ans = 0;

        boolean foundB = false;

        int removeA = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (foundB && c == 'a') {
                removeA++;
            } else if (!foundB && c == 'b') {
                foundB = true;
            }
        }

        boolean foundA = false;

        int removeB = 0;

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (foundA && c == 'b') {
                removeB++;
            } else if (!foundA && c == 'a') {
                foundA = true;
            }
        }

        return Math.min(removeA, removeB);
    }

    public int try_20260207_fail(String s) {
        int n = s.length();

        int countA = 0;
        int countB = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                countA++;
            } else {
                countB++;
            }
        }

        int ans = Math.min(countA, countB);

        int i = 0;
        int j = 1;

        while (i < n) {
            while (j < n && s.charAt(i) == s.charAt(j)) {
                j++;
            }

            char prev = s.charAt(i);
            char cur = j < n ? s.charAt(j) : prev;

            if (prev > cur) {

            }
        }

        return 0;
    }

    public int others(String s) {

        if (s == null || s.isEmpty()) return 0;

        int countB = 0; //keep a count of Bs
        int removals = 0; //keep a count of removed As

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == 'a') {
                if (countB > 0) { // only if there are Bs before this A
                    ++removals; // remove this A
                    --countB; // and decrement the Bs count
                }
            } else {
                ++countB; // keep incrementing the Bs count
            }
        }
       return removals;
}
}