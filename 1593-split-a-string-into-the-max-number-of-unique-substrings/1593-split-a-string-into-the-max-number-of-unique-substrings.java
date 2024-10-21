class Solution {
    public int maxUniqueSplit(String s) {
        return topdown(s);
    }

    public int topdown(String s) {
        Set<String> set = new HashSet();

        return topdown(s, 0, set);
    }

    public int topdown(String s, int index, Set<String> set) {
        if (index >= s.length()) return set.size();

        int ans = 0;

        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);

            if (set.add(sub)) {
                ans = Math.max(ans, topdown(s, i + 1, set));

                set.remove(sub);
            }
        }

        return ans;
    }
}