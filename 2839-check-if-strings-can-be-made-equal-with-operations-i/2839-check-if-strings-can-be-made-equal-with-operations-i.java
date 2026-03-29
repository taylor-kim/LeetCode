class Solution {
    public boolean canBeEqual(String s1, String s2) {
        return mySol(s1, s2);
    }

    public boolean mySol(String s1, String s2) {
        Set[] sets1 = {new HashSet(), new HashSet()};
        Set[] sets2 = {new HashSet(), new HashSet()};


        for (int i = 0; i < s1.length(); i++) {
            sets1[i % 2].add(s1.charAt(i));
            sets2[i % 2].add(s2.charAt(i));
        }

        return sets1[0].equals(sets2[0]) && sets1[1].equals(sets2[1]);
    }
}