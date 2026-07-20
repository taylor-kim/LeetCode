class Solution {
    public int findLUSlength(String a, String b) {
        return gemini(a, b);
    }

    public int gemini(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
    
    public int mySol(String a, String b) {
        if (a.equals(b)) {
            Set<Character> setA = getSet(a);
            Set<Character> setB = getSet(b);

            if (setA.size() > 1) {
                return a.length() - 1;
            } else {
                return -1;
            }
        } else {
            return Math.max(a.length(), b.length());
        }
    }

    private Set<Character> getSet(String s) {
        Set<Character> set = new HashSet();

        for (char c : s.toCharArray()) {
            set.add(c);
        }

        return set;
    }
}