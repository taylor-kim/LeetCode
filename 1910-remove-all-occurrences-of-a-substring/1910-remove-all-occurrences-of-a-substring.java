class Solution {
    public String removeOccurrences(String s, String part) {
        return mySol(s, part);
    }

    public String mySol(String s, String part) {
        int index = -1;

        while ((index = s.indexOf(part)) >= 0) {
            s = s.substring(0, index) + s.substring(index + part.length());

            // System.out.println(String.format("index:%d, s:%s", index, s));
        }

        return s;
    }
}