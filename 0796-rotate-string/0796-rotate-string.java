class Solution {
    public boolean rotateString(String s, String goal) {
        return mySol(s, goal);
    }

    public boolean mySol(String s, String goal) {
        if (s.length() != goal.length()) return false;

        return (s + s).indexOf(goal) >= 0;
    }
}