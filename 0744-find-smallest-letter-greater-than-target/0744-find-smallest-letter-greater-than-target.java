class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        return mySol(letters, target);
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