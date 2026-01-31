class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        return mySol2(letters, target);
    }

    public char mySol2(char[] letters, char target) {
        int lo = 0;
        int hi = letters.length;
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (letters[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return letters[lo % letters.length];
    }

    public char mySol(char[] letters, char target) {
        for (char c : letters) {
            if (target < c) {
                return c;
            }
        }

        return letters[0];
    }
}