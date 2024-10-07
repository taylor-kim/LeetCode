class Solution {
    public int minLength(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        if (s.indexOf("AB") >= 0 || s.indexOf("CD") >= 0 ) {
            s = s.replaceAll("AB", "").replaceAll("CD", "");

            return mySol(s);
        }

        return s.length();
    }
}