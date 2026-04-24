class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        return mySol(moves);
    }

    public int mySol(String moves) {
        int pos = 0;
        int extra = 0;

        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                pos--;
            } else if (c == 'R') {
                pos++;
            } else {
                extra++;
            }
        }

        return Math.abs(pos) + extra;
    }
}